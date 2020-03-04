package com.clinica.service;

import java.util.List;
import com.clinica.model.Especialidad;

public interface IEspecialidadService {

	public List<Especialidad> listarEspecialidades();
	public Especialidad obtenerEspecialidad(int id);		
	public Especialidad guardarEspecialidad(Especialidad p);
	
}
