package br.com.sgc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
        .cors(cors -> cors.configurationSource(request -> {
    var corsConfiguration = new org.springframework.web.cors.CorsConfiguration();
    corsConfiguration.setAllowedOrigins(java.util.List.of("http://localhost:5173"));
    corsConfiguration.setAllowedMethods(java.util.List.of("GET", "POST", "PUT", "DELETE"));
    corsConfiguration.setAllowedHeaders(java.util.List.of("*"));
    return corsConfiguration;
}))
            .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.POST, "/auth/login").permitAll() // Rota de login liberada
                .requestMatchers(HttpMethod.GET, "/produtos").permitAll()
                .requestMatchers(HttpMethod.POST, "/produtos").permitAll()
                .requestMatchers(HttpMethod.POST, "/auth/registrar").permitAll() // Rota de cadastro liberada
                .anyRequest().authenticated() // TODO O RESTO ESTÁ TRANCADO!
            )
            .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class); // Adiciona nosso filtro
        
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
