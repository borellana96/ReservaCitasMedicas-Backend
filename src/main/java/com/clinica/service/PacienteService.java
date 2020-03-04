package com.clinica.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.clinica.model.Paciente;
import com.clinica.repository.IPacienteRepository;

@Service
public class PacienteService implements IPacienteService{

	@Autowired
	private IPacienteRepository pacienteRepository;
	
	@Override
	public Paciente obtenerPaciente(String id) {
		return pacienteRepository.getOne(id);
	}

	@Override
	public List<Paciente> listarPacientes() {
		return pacienteRepository.findAll();
	}

	@Override
	public Paciente guardarPaciente(Paciente p) {
		Paciente paciente = pacienteRepository.save(p);	
		return paciente;
	}

}
