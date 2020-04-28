package com.clinica.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.clinica.model.Usuario;
import com.clinica.security.JwtUtil;
import com.clinica.service.IUsuarioService;
import com.clinica.service.UserDetailsImpl;
import com.clinica.util.JwtResponse;

@RestController
@RequestMapping("/api/clinica")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private IUsuarioService usuarioService;

	@PostMapping("/auth/signup")
	public ResponseEntity<?> signUp(@RequestBody Usuario user) {
		return usuarioService.guardarUsuario(user);
	}

	// EN POSTMAN:
	/*
	 * { "dni":"73524246", "password":"contraseña", "perfil":"paciente" }
	 */

	@PostMapping("/auth/signin")
	public ResponseEntity<?> signIn(@Valid @RequestBody Usuario user) {
		System.out.println("AUTHCONTROLLER......");
		// Provider que proporciona al AuthenticationManager los detalles de nuestro usuario.
		// Así, el provider construye un objeto UsernamePasswordAuthenticationToken
		// si la contraseña es la correcta y se lo pasa al AuthenticationManager.
		UsernamePasswordAuthenticationToken var = new UsernamePasswordAuthenticationToken(user.getUsername(),
																						  user.getPassword());
		System.out.println(var);

		// Representa el token para una solicitud de autenticación o
		// para un principal autenticado una vez procesado el sgte método
		Authentication authentication = authenticationManager.authenticate(var);
		System.out.println(authentication);
		
		// Se almacenará en un SecurityContext y será autenticada
		// por cualquier interceptor de seguridad (para método o invocaciones web)
		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = jwtUtil.generateJwtToken(authentication);

		System.out.print("Presentamos al objeto Autenticación: ");
		System.out.println(authentication.getPrincipal());
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		
		System.out.println("------------------");
		System.out.print("UserDetails: ");
		System.out.println(userDetails);
		System.out.println(userDetails.getUsername());
		System.out.println(userDetails.getPassword());
		System.out.println("------------------");
		
		 return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername()));
	}
	
	@GetMapping("/auth/hello-world")
	public ResponseEntity<String> helloWorl() {
		return ResponseEntity.ok("Hello World!");
	}

}
