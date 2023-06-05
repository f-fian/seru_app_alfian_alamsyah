package com.example.seru.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;


    String[] WHITE_LIST = {
            "vehicle-years/**",
            "vehicle-brands/**",
            "vehicle-types/**",
            "vehicle-models/**",
            "price-list/**",
            "error"
    };

    String[] ADMIN_URL = {"test-admin","user/**"};


    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .cors().disable()
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.GET,WHITE_LIST).permitAll()
                .requestMatchers("authenticate","register").permitAll()
                .requestMatchers(HttpMethod.POST,WHITE_LIST).hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.PUT,WHITE_LIST).hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.DELETE,WHITE_LIST).hasAuthority("ADMIN")
                .requestMatchers(ADMIN_URL).hasAuthority("ADMIN")
                .requestMatchers("test-user").hasAuthority("USER")

                .and()
                .httpBasic()
                .and()
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();

    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Autowired
    void registerProvider(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(customAuthenticationProvider);
    }
}
