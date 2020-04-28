package com.clinica.service;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.clinica.model.Usuario;

public interface IUsuarioService {

	public Usuario obtenerUsuario(String id);

	public List<Usuario> listarUsuarios();

	public ResponseEntity<?> guardarUsuario(Usuario u);

	public Usuario loginUsuario(Usuario u);

}
