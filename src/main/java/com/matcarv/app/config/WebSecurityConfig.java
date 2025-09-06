package com.matcarv.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuration class for setting up web security using Spring Security.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    /**
	 * 
	 * @return
	 */
	@Bean
	UserDetailsService userDetailsService() {
        final UserDetails user = User.withUsername("admin")
				.password(passwordEncoder().encode("123456"))
				.roles("USER")
				.build();

		return new InMemoryUserDetailsManager(user);
	}

    /**
	 * 
	 * @param http
	 * @return
	 * @throws Exception
	 */
	@Bean
    SecurityFilterChain filterChain(final HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable());
        http.authorizeHttpRequests(auth -> auth
            .requestMatchers(
                "/v2/api-docs/**", 
                "/swagger-ui/**", 
                "/swagger-resources/**",
                "/webjars/**", 
                "/configuration/**", 
                "/", 
                "/docs"
            ).permitAll()
            .anyRequest().authenticated()
        )
        .httpBasic(Customizer.withDefaults());

		return http.build();
	}
	
	/**
	 * 
	 * @return
	 */
	@Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
