package com.clinica.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.clinica.model.Especialidad;
import com.clinica.repository.IEspecialidadRepository;

@Service
public class EspecialidadService implements IEspecialidadService {

	@Autowired
	private IEspecialidadRepository especialidadRepository;

	@Override
	public Especialidad obtenerEspecialidad(int id) {
		return especialidadRepository.getOne(id);
	}

	@Override
	public List<Especialidad> listarEspecialidades() {
		return especialidadRepository.findAll();
	}

	@Override
	public Especialidad guardarEspecialidad(Especialidad e) {
		Especialidad especialidad = especialidadRepository.save(e);
		return especialidad;
	}
}
