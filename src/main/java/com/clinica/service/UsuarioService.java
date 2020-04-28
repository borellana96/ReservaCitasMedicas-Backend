package com.clinica.service;

import java.util.List;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
import com.clinica.model.Usuario;
import com.clinica.repository.IUsuarioRepository;

//@Service
public class UsuarioService {

	//@Autowired
	private IUsuarioRepository usuarioRepository;
	
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
	
	
	public Usuario obtenerUsuario(String id) {	
		return usuarioRepository.getOne(id);
	}

	
	public List<Usuario> listarUsuarios() {
		return usuarioRepository.findAll();
	}

	
	public Usuario guardarUsuario(Usuario u) {
		/*
		 * if(usuarioRepository.existsbyUsername(u.getUsername())){ return
		 * ResponseEntity .badRequest().body("Error: Username is already taken!"); }
		 */
		// Encriptando la contraseña
		String encodedPassword = encoder.encode(u.getPassword());

		// Seteando la contraseña
		u.setPassword(encodedPassword);

		// Obtener el usuario guardado
		Usuario user = usuarioRepository.save(u);

		return user;
	}
	
	public Usuario loginUsuario(String dni, String password){
		//Usuario extraido de la DB
		Usuario userObtenido = usuarioRepository.getOne(dni);
	
		//Comparamos password de: usuario post y usuario de la DB
		boolean band = encoder.matches(password, userObtenido.getPassword());
	
        if(band)
        	return userObtenido;
        else
        	return null;	
	}
	
}
