package com.sparksupport.product.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
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
 * Security configuration class. Configures Spring Security settings for the
 * application.
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	/**
	 * Provides a PasswordEncoder bean using BCrypt.
	 *
	 * @return BCryptPasswordEncoder
	 */
	@Bean
	static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * Configures the SecurityFilterChain for HTTP security.
	 *
	 * @param httpSecurity HttpSecurity object to configure
	 * @return SecurityFilterChain
	 * @throws Exception If an error occurs during configuration
	 */
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

		httpSecurity.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests((authorize) -> authorize.anyRequest().authenticated())
				.httpBasic(Customizer.withDefaults());

		return httpSecurity.build();
	}

	/**
	 * Provides a basic UserDetailsService with in-memory user details.
	 *
	 * @return InMemoryUserDetailsManager
	 */
	@Bean
	UserDetailsService userDetailsService() {
		UserDetails kailas = User.builder().username("kailas").password(passwordEncoder().encode("kailas"))
				.roles("USER").build();

		UserDetails spark = User.builder().username("spark").password(passwordEncoder().encode("spark")).roles("ADMIN")
				.build();

		return new InMemoryUserDetailsManager(kailas, spark);
	}

}
