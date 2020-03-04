package com.clinica.service;

import java.util.List;
import com.clinica.model.Usuario;

public interface IUsuarioService {
	public Usuario obtenerUsuario(String id);	
	public List<Usuario> listarUsuarios();	
	public Usuario guardarUsuario(Usuario u);
	public Usuario loginUsuario(String dni, String password);
}
