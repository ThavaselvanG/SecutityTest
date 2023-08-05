package com.demo.app.demo.configuration;

 import com.demo.app.demo.exceptionhandler.AuthExceptionHandler;
import com.demo.app.demo.jwt.JwtAuthFilter;
 import com.demo.app.demo.apis.accounts.UserDetailsServiceImpl;
 import com.demo.app.demo.test.ApiLive;
import com.demo.app.demo.test.ApiTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.demo.app.demo.constant.AppConstant.*;

@Configuration
public class SecurityConfig {
    @Autowired
    AuthExceptionHandler authExceptionHandler;
    @Autowired
    UserDetailsServiceImpl userDetailsService;
    @Autowired
    private JwtAuthFilter authFilter;

    @Bean
    public SecurityFilterChain webApi(HttpSecurity http) throws Exception {
        return http.csrf().disable().httpBasic().authenticationEntryPoint(authExceptionHandler).
                and().authorizeHttpRequests()
                .requestMatchers(AUTH_BASE_URLS).permitAll()
                .requestMatchers(SWAGGER_UI).permitAll()
                .and().authorizeHttpRequests()
                .requestMatchers(USER_BASE_URLS).hasRole("USER")
                .requestMatchers(ADMIN_BASE_URLS).hasRole("ADMIN")
                .anyRequest().authenticated()
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authenticationProvider(authenticationProvider())
                .exceptionHandling().accessDeniedHandler(authExceptionHandler).authenticationEntryPoint(authExceptionHandler)
                .and().addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class).

                build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    @Profile("default")
    public ApiLive apiLive() {
        return new ApiLive();
    }

    @Bean
    @Profile("test")
    public ApiTest apiTest() {
        return new ApiTest();
    }
}
