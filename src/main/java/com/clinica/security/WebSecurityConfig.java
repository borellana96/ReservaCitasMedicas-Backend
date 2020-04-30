package com.clinica.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.clinica.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	UserDetailsServiceImpl userDetailsService;
	
	@Autowired
	private JwtEntryPoint unauthorizedHandler;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	protected void configure(HttpSecurity http) throws Exception {
		http
		.cors().and()
        .csrf().disable()
        .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
        .addFilterAfter(new JwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
        .authorizeRequests()
        .antMatchers("/api/clinica/auth/**").permitAll()		//permitimos el acceso a /login a cualquiera
        //.antMatchers("/**")
        .anyRequest().authenticated();				//cualquier otra peticion requiere autenticacion 
		
    }
	
	/*
	 * En la clase CorsRegistration que se encuenta dentro de CorsRegistry, 
	 * sus métodos retornan el mismo objeto, así que se puede seguir llamando 
	 * a más metodos de esa clase(CorsRegistration) y así seguir añadiendo más
	 * configuraciones al CORS. Algunos de estos métodos: allowedHeaders,
	 * allowedMethods, allowedOrigins, maxAge, etc.
	 */
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://localhost:4200")
				.allowedMethods("GET", "POST");
			}
		};
	}
	
}
