package fr.insys.commerce.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import fr.insys.commerce.security.jwt.AuthEntryPointJwt;
import fr.insys.commerce.security.jwt.AuthTokenFilter;
import fr.insys.commerce.security.services.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	UserDetailsServiceImpl userDetailsService;
	@Autowired
	private AuthEntryPointJwt unauthorizedHandler;

	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}

	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()

				.antMatchers("/frais/all-with-id").access("hasRole('COMMERCIAL')")
				.antMatchers("/frais/create").access("hasRole('COMMERCIAL')")
				.antMatchers("/frais/{\\d+}").access("hasRole('COMMERCIAL')")
				.antMatchers("/frais/{\\d+}/delete").access("hasRole('COMMERCIAL')")
				.antMatchers("/frais/{\\d+}/update").access("hasRole('COMMERCIAL')")
				
				.antMatchers("/tag/all-with-id").access("hasRole('COMMERCIAL')")
				.antMatchers("/tag/create").access("hasRole('COMMERCIAL')")
				.antMatchers("/tag/{\\d+}").access("hasRole('COMMERCIAL')")
				.antMatchers("/tag/{\\d+}/delete").access("hasRole('COMMERCIAL')")
				.antMatchers("/tag/{\\d+}/update").access("hasRole('COMMERCIAL')")

				.antMatchers("/type-produit/all-with-id").access("hasRole('COMMERCIAL')")
				.antMatchers("/type-produit/create").access("hasRole('COMMERCIAL')")
				.antMatchers("/type-produit/{\\d+}").access("hasRole('COMMERCIAL')")
				.antMatchers("/type-produit/{\\d+}/delete").access("hasRole('COMMERCIAL')")
				.antMatchers("/type-produit/{\\d+}/update").access("hasRole('COMMERCIAL')")
				
				.antMatchers("/users").access("hasRole('ADMIN')")
				.antMatchers("/users/id:**").access("hasRole('ADMIN') or hasRole('COMMERCIAL')")
				.antMatchers("/users/mail:**").access("hasRole('ADMIN') or hasRole('COMMERCIAL')")
				.antMatchers("/users/nom:**").access("hasRole('ADMIN') or hasRole('COMMERCIAL')")
				.antMatchers("/users/{\\d+}/delete").access("hasRole('ADMIN')")
				.antMatchers("/users/login").access("isAnonymous()")
				
				.antMatchers(HttpMethod.POST, "/produit/create").access("hasRole('COMMERCIAL')")
				.antMatchers(HttpMethod.DELETE, "/produit/{\\d+}").access("hasRole('COMMERCIAL')")
				.antMatchers(HttpMethod.DELETE, "/produit/{\\d+}/disable").access("hasRole('COMMERCIAL')")
				.antMatchers(HttpMethod.PUT, "/produit/{\\d+}").access("hasRole('COMMERCIAL')")
				
				.antMatchers("/**").permitAll().anyRequest().authenticated();
		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	}
}