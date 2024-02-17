package com.TruckBooking.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebSecurity
@EnableWebMvc
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	public static final String[] PUBLIC_URLS = {
		"/api/v1/auth/**",
		"/v3/api-docs",
		"/v2/api-docs",
		"/swagger-resources/**",
		"/swagger-ui/**",
		"/webjars/**"
	};
		
	@Bean
	public FirebaseAuthenticationFilter firebaseAuthenticationFilter() {
		return new FirebaseAuthenticationFilter();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.csrf().disable()
				.authorizeRequests()
				.antMatchers("/public/**").permitAll() // Allow public APIs

				.antMatchers("/invoice/**").authenticated() // only Invoice service is authenticated
				//.anyRequest().authenticated(); // to authenticate all api's uncomment this
				.anyRequest().permitAll();

		// Add FirebaseAuthenticationFilter before UsernamePasswordAuthenticationFilter
		http
				.addFilterBefore(firebaseAuthenticationFilter(),UsernamePasswordAuthenticationFilter.class)
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
}
