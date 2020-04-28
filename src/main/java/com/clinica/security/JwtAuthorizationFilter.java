package com.clinica.security;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.clinica.service.UserDetailsServiceImpl;

public class JwtAuthorizationFilter extends OncePerRequestFilter {
	
	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	private static final Logger logger = LoggerFactory.getLogger(JwtAuthorizationFilter.class);
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, 
									HttpServletResponse response,
									FilterChain filterChain) throws ServletException, IOException {
		System.out.println("JwtAuthorizationFilter......INICIO");
		try {
			String jwt = parseJwt(request);
			System.out.println(jwtUtil.validateJwtToken(jwt));
			System.out.println("Este es el token completo: " + jwt);
			if (jwt != null && jwtUtil.validateJwtToken(jwt)) {
				
				System.out.println(jwtUtil.validateJwtToken(jwt));
				String username = jwtUtil.getUserNameFromJwtToken(jwt);
				System.out.println(username);
				
				UserDetails userDetails = userDetailsService.loadUserByUsername(username);
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails, null);
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		} catch (Exception e) {
			logger.error("Cannot set user authentication: {}", e);
		}
		System.out.println("JwtAuthorizationFilter......FIN");
		filterChain.doFilter(request, response);
		
	}

	private String parseJwt(HttpServletRequest request) {
		String headerAuth = request.getHeader("Authorization");

		if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
			return headerAuth.substring(7, headerAuth.length());
		}

		return null;
	}
}
