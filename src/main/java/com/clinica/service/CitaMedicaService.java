package com.clinica.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.clinica.model.CitaMedica;
import com.clinica.model.Medico;
import com.clinica.model.Paciente;
import com.clinica.repository.ICitaMedicaRepository;

@Service
public class CitaMedicaService implements ICitaMedicaService{

	@Autowired
	private ICitaMedicaRepository citaRepository;
	
	@Autowired
	private MedicoService medicoS;
	
	@Autowired
	private PacienteService pacienteS;
	
	@Override
	public List<CitaMedica> obtenerCitaxDni(String dni) {
		List<CitaMedica> lista = citaRepository.findAll();
		List<CitaMedica> listaPacientes = lista
				.stream()
				.filter(p -> p.getPaciente_id().getDni().equals(dni))
				.collect(Collectors.toList());
		return listaPacientes;	
	}
	
	@Override
	public CitaMedica obtenerCita(int id) {
		return citaRepository.getOne(id);
	}

	@Override
	public List<CitaMedica> listarCitas() {
		return citaRepository.findAll();
	}

	@Override
	public CitaMedica guardarCita(CitaMedica cita) {
		cita.setEstado("Proceso");
		cita.setEstadoPago(false);
		
		//GUARDANDO PACIENTE EN CITA
		String idPaciente = cita.getPaciente_id().getDni();		//Obtener el Id del paciente	
		Paciente p = pacienteS.obtenerPaciente(idPaciente);		//Buscar y capturar al paciente	
		cita.setPaciente_id(p);									//Setear al paciente dentro de los campos de cita
		
		//GUARDANDO MEDICO EN CITA
		int idMedico = cita.getMedico_id().getIdMedico();
		Medico m = medicoS.obtenerMedico(idMedico);
		cita.setMedico_id(m);

		return citaRepository.save(cita);
	}

}
