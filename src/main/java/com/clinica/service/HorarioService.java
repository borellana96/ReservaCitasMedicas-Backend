package com.clinica.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.clinica.model.Horario;
import com.clinica.model.Medico;
import com.clinica.repository.IHorarioRepository;

@Service
public class HorarioService implements IHorarioService {

	@Autowired
	private IHorarioRepository horarioRepository;

	@Autowired
	private MedicoService medicoS;

	public List<Horario> obtenerHorarioxMedico(int idMedico) {
		List<Horario> lista = horarioRepository.findAll();
		List<Horario> listaHorarios = lista
				.stream()
				.filter(h -> h.getMedico_id().getIdMedico() == idMedico)
				.collect(Collectors.toList());
		return listaHorarios;
	}

	@Override
	public List<Horario> listarHorarios() {
		return horarioRepository.findAll();
	}

	@Override
	public Horario obtenerHorario(int id) {
		return horarioRepository.getOne(id);
	}

	@Override
	public Horario guardarHorario(Horario h) {
		// GUARDANDO MEDICO EN HORARIO
		int idMedico = h.getMedico_id().getIdMedico(); 		// Obtener el Id del medico
		Medico objMedico = medicoS.obtenerMedico(idMedico); // Buscar y capturar al objeto medico
		h.setMedico_id(objMedico); 							// Setear el medico dentro del campo de horario

		Horario horario = horarioRepository.save(h);
		return horario;
	}

	@Override
	public List<Horario> guardarHorarios(List<Horario> h) {
		// GUARDANDO MEDICO EN HORARIO
		int idMedico;
		Medico objMedico;
		List<Horario> aux = horarioRepository.findAll();		// Obtener la lista actual de horarios del Repo
		
		for (int i = 0; i < h.size(); i++) {
			idMedico = h.get(i).getMedico_id().getIdMedico(); 	// Obtener el Id del medico
			objMedico = medicoS.obtenerMedico(idMedico); 		// Buscar y capturar al objeto medico
			h.get(i).setMedico_id(objMedico);					// Setear el medico dentro del campo de horario
			aux.add(h.get(i));									// Añadimos un nuevo horario a la lista actual
		}
		return horarioRepository.saveAll(aux);					// Guardamos la lista actualizada de horarios en el repo
	}

}
