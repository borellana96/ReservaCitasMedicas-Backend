package com.clinica.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.clinica.model.Usuario;
import com.clinica.service.IUsuarioService;

@RestController
@RequestMapping("/clinica")
public class UsuarioController {

	@Autowired
	private IUsuarioService usuarioService;

	@GetMapping("/usuarios")
	public List<Usuario> listar() {
		return usuarioService.listarUsuarios();
	}

	@GetMapping("/usuarios/{id}")
	public ResponseEntity<Usuario> obtenerUsuario(@PathVariable(value="id") String idUser) {
		Usuario user = usuarioService.obtenerUsuario(idUser);
		if (user.equals(null))
			return ResponseEntity.notFound().build();

		return ResponseEntity.ok().body(user);
	}

	@PostMapping("/usuarios")
	public Usuario guardarUsuario(@RequestBody Usuario user) {
		return usuarioService.guardarUsuario(user);
	}
	//EN POSTMAN:
	/*{
		"dni":"73524246",
		"password":"contraseña",
		"perfil":"paciente"
	}*/
	
	@GetMapping("/login/{dni}/{password}")
	public Usuario login(@PathVariable(value="dni") String dni, @PathVariable(value="password") String password) {
		return usuarioService.loginUsuario(dni, password);
	}
}
