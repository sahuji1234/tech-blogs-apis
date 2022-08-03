package com.suraj.blogs.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private UserDetailsService userDetailsSerice;

	@Autowired
	private JwtTokenHelper tokenHelper;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		// get Token

		String requestToken = request.getHeader("Authorization");

		// Bearer Token

		System.out.println(requestToken);

		String username = null;
		String token = null;

		if (requestToken != null && requestToken.startsWith("Bearer")) {
			token = requestToken.substring(7);
			try {
				username = this.tokenHelper.extractUsername(token);
			} catch (IllegalArgumentException e) {
				
			} catch (ExpiredJwtException e) {
				
			}catch (MalformedJwtException e) {
				
			}

		} else {
			System.out.println("Jwt Token Does not begin with Bearer");
		}
 
		
		// once we got the token now validate 
		
		
		if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			 UserDetails userDetails = this.userDetailsSerice.loadUserByUsername(username);
			if(this.tokenHelper.validateToken(token, userDetails)) {
				// token is valid 
				// authentication
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToke = new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
				usernamePasswordAuthenticationToke.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToke);
			}else {
				System.out.println("Invalid Jwt token");
			}
			
		}else {
			System.out.println("username is null or context is null");
		}
		
		
		filterChain.doFilter(request, response);
	}

}
