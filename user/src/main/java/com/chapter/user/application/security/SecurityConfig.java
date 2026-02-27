package com.chapter.user.application.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SecurityConfig {
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final UserDetailsImpl userDetailsImpl;

    public SecurityConfig(PasswordEncoder passwordEncoder, JwtUtil jwtUtil, UserDetailsImpl userDetailsImpl) {
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.userDetailsImpl = userDetailsImpl;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(http -> {
                    // EndPoints publicos
                    http.requestMatchers(HttpMethod.POST, "/user/auth/**", "/usuario/cliente").permitAll();
                    http.requestMatchers("/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**").permitAll();
                    http.requestMatchers(HttpMethod.POST, "/usuario/admin").permitAll();

                    // EndPoints Privados
                    http.requestMatchers(HttpMethod.GET, "/usuario/admin/**").hasAuthority("ROLE_1");
                    http.requestMatchers("/usuario/propietario/**").hasAuthority("ROLE_2");
                    http.requestMatchers(HttpMethod.GET, "/usuario/cliente/{id}").hasAuthority("ROLE_4");

                    http.anyRequest().authenticated();
                })
                .addFilterBefore(new JwtTokenValidator(jwtUtil), BasicAuthenticationFilter.class)
                .build();
    }

}
