package com.clinica.service;

import java.util.List;
import com.clinica.model.Medico;

public interface IMedicoService {

	public List<Medico> obtenerMedicoxEspecialidad(int idEspecialidad);
	public Medico obtenerMedico(int id);	
	public List<Medico> listarMedicos();	
	public Medico guardarMedico(Medico p);
	
}
