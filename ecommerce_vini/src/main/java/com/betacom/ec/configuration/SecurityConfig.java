package com.betacom.ec.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // 1. Disabilita CSRF per permettere POST/PUT/DELETE da Swagger e Postman
            .csrf(csrf -> csrf.disable())
            
            // 2. Regola le autorizzazioni
            .authorizeHttpRequests(auth -> auth
                // Consente l'accesso pubblico alle rotte API di sviluppo e Swagger
                .requestMatchers("/rest/api/**", "/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html", "/h2-console/**").permitAll()
                .anyRequest().authenticated()
            )
            
            // Per abilitare l'interfaccia di H2 console se la usi
            .headers(headers -> headers.frameOptions(frame -> frame.disable()));

        return http.build();
    }
}