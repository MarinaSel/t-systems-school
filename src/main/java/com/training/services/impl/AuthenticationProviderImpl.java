package com.training.services.impl;

import com.training.models.User;
import com.training.services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
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

/**
 * Class that implements AuthenticationProvider and is used for users authentication.
 *
 * @see UserService
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


        if (driverService.findByUser(user) == null) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else {
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }
        return new UsernamePasswordAuthenticationToken(login, password, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
