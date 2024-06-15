package com.pontoflowatual.pfw.seguranca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain setsecurityFilterChain(final HttpSecurity httpSecurity) throws Exception {

        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(autorize -> autorize
                        .requestMatchers(HttpMethod.POST, "/api/usuarios/login","/api/usuarios/criar").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/usuarios/login","/api/usuarios/getByToken").permitAll()   
                        .requestMatchers(HttpMethod.POST, "/api/funcionarios").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/funcionarios/{id}").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/departamentos").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/departamentos/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/funcionarios").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/departamentos").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/solicitarAusencia").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/solicitarAusencia").permitAll()        
                        .requestMatchers(HttpMethod.GET, "/api/tipoausencia").permitAll() 
                        .requestMatchers(HttpMethod.POST, "/api/tipoausencia").permitAll()    
                        .requestMatchers(HttpMethod.GET, "/api/departamentos/{id}/funcionarios").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/funcionarios/buscarPorEmail").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/funcionarios/buscarPorNome").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/usuarios/login").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/usuarios/login","/api/api/usuarios/getUserDetails").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/usuarios/getProfile").permitAll()
                        
                        .anyRequest().authenticated()
                 )
                 .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                 .build();

    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {

        return authenticationConfiguration.getAuthenticationManager();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
