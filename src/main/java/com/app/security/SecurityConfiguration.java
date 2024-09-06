package com.app.security;

import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http
	        .cors(withDefaults()) // Enable CORS
	        .authorizeHttpRequests(auth -> auth
	            // Protect Swagger UI and API docs with authentication
	            .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").authenticated()
	            // Restrict access to /users/** to users with the ADMIN role
	            .requestMatchers("/users/**").hasRole("ADMIN")
	            // All other requests need authentication
	            .anyRequest().authenticated()
	        )
	        // Use HTTP Basic Authentication for prompting credentials
	        .httpBasic(withDefaults())  
	        .logout(logout -> 
	            logout.permitAll()
	        )
	        .csrf(csrf -> csrf.disable());  // Disable CSRF for simplicity

	    return http.build();
	}

@Bean
public InMemoryUserDetailsManager userDetailsService() {
    UserDetails admin = User.withUsername("admin")
            .password(passwordEncoder().encode("password"))
            .roles("ADMIN")
            .build();
    return new InMemoryUserDetailsManager(admin);
}

@Bean
public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}
}
/*
login to app
http://localhost:8086/login
x-www-form-urlencoded
username: admin
password:password
*/