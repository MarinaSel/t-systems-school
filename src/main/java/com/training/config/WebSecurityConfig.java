package com.training.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@EnableWebSecurity
@ComponentScan(basePackages = {"com.training.services", "com.training.repositories"},
        basePackageClasses = DataConfig.class)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthenticationProvider authenticationProvider;
    private final AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    public WebSecurityConfig(@Qualifier("customAuthenticationProvider") AuthenticationProvider authenticationProvider,
                             @Qualifier("customAuthenticationSuccessHandler")
                                     AuthenticationSuccessHandler authenticationSuccessHandler) {
        this.authenticationProvider = authenticationProvider;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
    }

    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/home/**")
                .hasRole("USER")
                .and()
                .authorizeRequests().antMatchers("/api/vehicle/findVehicleWithLoads").hasRole("USER")
                .and()
                .authorizeRequests()
                .anyRequest().hasRole("ADMIN")
                .and()
                .formLogin().permitAll().loginPage("/loginPage").successHandler(authenticationSuccessHandler)
                .and()
                .logout().permitAll().logoutSuccessUrl("/loginPage?logout")
                .and()
                .httpBasic()
                .and()
                .exceptionHandling().accessDeniedPage("/accessDenied");
        ;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider);
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/resources/**");
    }
}
