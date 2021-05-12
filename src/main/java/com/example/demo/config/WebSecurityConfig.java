package com.example.demo.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private static final String USERNAME_QUERY = "SELECT user_name AS username,`password` ,1 AS enabled FROM `appointmentreservation`.`person` WHERE user_name = ?";

	private static final String AUTHORITIES_BY_USERNAME_QUERY = "SELECT  user_name AS username, roles AS role  FROM `appointmentreservation`.`person` per   JOIN `appointmentreservation`.`person_roles` AS role WHERE per.id = role.person_id AND user_name = ?";

	@Autowired
	private DataSource dataSource;
	
	// ignores the swagger ui
	
	 private static final String[] AUTH_WHITELIST = {
	            // -- Swagger UI v2
	            "/v2/api-docs",
	            "/swagger-resources",
	            "/swagger-resources/**",
	            "/configuration/ui",
	            "/configuration/security",
	            "/swagger-ui.html",
	            "/webjars/**",
	            // -- Swagger UI v3 (OpenAPI)
	            "/v3/api-docs/**",
	            "/swagger-ui/**"
	            // other public endpoints of your API may be appended to this array
	    };

	@Autowired
	protected void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		PasswordEncoder encoder = new BCryptPasswordEncoder();

		auth.inMemoryAuthentication().passwordEncoder(encoder).withUser("service").password(encoder.encode("123"))
				.roles("Service");

		auth.jdbcAuthentication().passwordEncoder(encoder).dataSource(dataSource).usersByUsernameQuery(USERNAME_QUERY)
				.authoritiesByUsernameQuery(AUTHORITIES_BY_USERNAME_QUERY);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
				.antMatchers("/favicon.ico").permitAll()
				.antMatchers("/GET/provider/session").permitAll()
				.antMatchers("/GET/provider/session/**").permitAll()
				.antMatchers("/DELETE/provider/session/**").hasRole("Admin")	
				.antMatchers("/PUT/provider/session/**").permitAll()	
			//	.antMatchers(AUTH_WHITELIST).permitAll()
				.anyRequest().authenticated()				
				.and()
				.formLogin();

		

	}

}
