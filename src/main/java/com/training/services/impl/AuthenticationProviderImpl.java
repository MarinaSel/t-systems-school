package com.training.services.impl;

import com.training.models.Driver;
import com.training.models.User;
import com.training.services.DriverService;
import com.training.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.training.entities.enums.DriverStatus.FIRED;

/**
 * Class that implements AuthenticationProvider and is used for users authentication.
 *
 * @see UserServiceImpl
 * @see DriverService
 * @see PasswordEncoder
 */
@Service("customAuthenticationProvider")
public class AuthenticationProviderImpl implements AuthenticationProvider {

    private final UserService userService;

    private final DriverService driverService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationProviderImpl(UserService userService, DriverService driverService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.driverService = driverService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String login = authentication.getName();
        User user = userService.findByLogin(login);

        if (user == null) {
            throw new UsernameNotFoundException("Wrong login");
        }
        String password = authentication.getCredentials().toString();

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Wrong password");
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        Driver driver = driverService.findByUser(user);

        if (driver == null) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else {
            if (driver.getStatus() == FIRED) {
                throw new LockedException("You were fired");
            } else {
                authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            }
        }
        return new UsernamePasswordAuthenticationToken(login, password, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
