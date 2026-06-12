// package com.financemanager.financemanager.security;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.web.SecurityFilterChain;

// @EnableWebSecurity
// @Configuration
// public class SecurityConfig {

//         @Bean
//         public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//         http
//                 .csrf(csrf -> csrf.disable()) // Crucial for API testing tools
//                 .authorizeHttpRequests(auth -> auth
//                 .requestMatchers(
//                         "/v3/api-docs/**",    // <-- Swagger backend JSON data
//                         "/swagger-ui/**",     // <-- Swagger HTML/JS files
//                         "/swagger-ui.html"
//                 ).permitAll()
//                 .anyRequest().authenticated()
//                 )
//                 .formLogin(form -> form.permitAll())
//                 .httpBasic(basic -> {});

//         return http.build();
//         }
// }
