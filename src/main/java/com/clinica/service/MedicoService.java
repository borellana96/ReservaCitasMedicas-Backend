package com.clinica.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.clinica.model.Especialidad;
import com.clinica.model.Medico;
import com.clinica.repository.IMedicoRepository;

@Service
public class MedicoService implements IMedicoService{
	
	@Autowired
	private IMedicoRepository medicoRepository;
	
	@Autowired
	private EspecialidadService especialidadS;

	
	public List<Medico> obtenerMedicoxEspecialidad(int idEspecialidad) {
		List<Medico> lista = medicoRepository.findAll();
		List<Medico> listaMedicos = lista
				.stream()
				.filter(m -> m.getEspecialidad_id().getIdEspecialidad()== idEspecialidad)
				.collect(Collectors.toList());
		return listaMedicos;	
	}
	
	@Override
	public Medico obtenerMedico(int id) {
		return medicoRepository.getOne(id);
	}
	
	@Override
	public List<Medico> listarMedicos(){
		return medicoRepository.findAll();
	}
	
	@Override
	public Medico guardarMedico(Medico m) {
		//GUARDANDO ESPECIALIDAD EN MEDICO
		int idEspecialidad = m.getEspecialidad_id().getIdEspecialidad();	//Obtener el Id de la especialidad		
		Especialidad objEspecialidad = especialidadS.obtenerEspecialidad(idEspecialidad);	//Buscar y capturar la especialidad	
		m.setEspecialidad_id(objEspecialidad);				//Setear la especialidad dentro de los campos de médico
		
		Medico medico = medicoRepository.save(m);	//guardar el médico completo
		return medico;
	}

}
