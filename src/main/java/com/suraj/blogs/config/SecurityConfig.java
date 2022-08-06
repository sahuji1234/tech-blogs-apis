package com.suraj.blogs.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.suraj.blogs.security.CustomUserDetailsService;
import com.suraj.blogs.security.JwtAuthenticationEntryPoint;
import com.suraj.blogs.security.JwtAuthenticationFilter;


@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Autowired
	private JwtAuthenticationEntryPoint entryPoint;
	
	@Autowired
	private JwtAuthenticationFilter filter;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
		.csrf().disable()
		.authorizeHttpRequests()
		.antMatchers("/api/v1/auth/login").permitAll()
		.antMatchers(HttpMethod.GET).permitAll()
		.antMatchers("/api/v1/auth/register").permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.exceptionHandling().authenticationEntryPoint(this.entryPoint)
		.and()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.addFilterBefore(this.filter, UsernamePasswordAuthenticationFilter.class);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		
		return super.authenticationManagerBean();
	}

	@Bean
	public FilterRegistrationBean coresFilter() {
	     UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		 CorsConfiguration corsConfiguration = new CorsConfiguration();
		 corsConfiguration.setAllowCredentials(true);
		 corsConfiguration.addAllowedOriginPattern("*");
		 corsConfiguration.addAllowedHeader("Authorization");
		 corsConfiguration.addAllowedHeader("Content-Type");
		 corsConfiguration.addAllowedHeader("Accept");
		 corsConfiguration.addAllowedMethod("POST");
		 corsConfiguration.addAllowedMethod("GET");
		 corsConfiguration.addAllowedMethod("DELETE");
		 corsConfiguration.addAllowedMethod("PUT");
		 corsConfiguration.addAllowedMethod("OPTIONS");
		 corsConfiguration.setMaxAge(3600L);
		 
		 
		source.registerCorsConfiguration("/**", corsConfiguration);
		
		
	    FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
		
		return bean;
	}
	
}
