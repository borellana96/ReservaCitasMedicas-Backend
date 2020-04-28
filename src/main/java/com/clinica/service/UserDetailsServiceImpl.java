package com.clinica.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.clinica.model.Usuario;
import com.clinica.repository.IUsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService, IUsuarioService {

	@Autowired
	private IUsuarioRepository usuarioRepository;

	@Autowired
	PasswordEncoder encoder;
	//BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);

	@Override
	public Usuario obtenerUsuario(String id) {
		return usuarioRepository.getOne(id);
	}

	@Override
	public List<Usuario> listarUsuarios() {
		return usuarioRepository.findAll();
	}

	@Override
	public ResponseEntity<?> guardarUsuario(Usuario u) {
		if (usuarioRepository.existsById(u.getUsername())) {
			return ResponseEntity.badRequest().body("Error: Username is already taken!");
		}

		// Encriptando la contraseña
		String encodedPassword = encoder.encode(u.getPassword());

		// Seteando la contraseña
		u.setPassword(encodedPassword);

		// Obtener el usuario guardado
		Usuario user = usuarioRepository.save(u);
		return ResponseEntity.ok().body(user);
		//return ResponseEntity.ok("User registered successfully!");
	}

	public Usuario loginUsuario(Usuario user) {
		// Usuario extraido de la DB
		Usuario userObtenido = usuarioRepository.getOne(user.getUsername());

		// Comparamos password de: usuario post y usuario de la DB
		boolean band = encoder.matches(user.getPassword(), userObtenido.getPassword());

		if (band)
			return userObtenido;
		else
			return null;
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Usuario user = usuarioRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
		/*if (user == null) {
			throw new UsernameNotFoundException(username);
		}*/
		return new UserDetailsImpl(user);
	}

}
