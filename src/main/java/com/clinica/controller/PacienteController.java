package com.clinica.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinica.model.Paciente;
import com.clinica.service.IPacienteService;

@RestController
@RequestMapping("/clinica")
public class PacienteController {

	@Autowired
	private IPacienteService pacienteService;

	@GetMapping("/pacientes")
	public List<Paciente> listar() {
		return pacienteService.listarPacientes();
	}

	@GetMapping("/pacientes/{id}")
	public ResponseEntity<Paciente> obtenerPaciente(@PathVariable(value="id") String idPaciente) {
		Paciente pa = pacienteService.obtenerPaciente(idPaciente);
		if (pa.equals(null))
			return ResponseEntity.notFound().build();

		return ResponseEntity.ok().body(pa);
	}

	@PostMapping("/pacientes")
	public Paciente guardarPaciente(@RequestBody Paciente pa) {
		return pacienteService.guardarPaciente(pa);
	}
	//EN POSTMAN: 
	/*{
		"dni":"73524246",
		"nombres":"Brian Gerard",
		"apellidoPaterno":"Orellana",
		"apellidoMaterno":"Ita",
		"sexo":"Masculino",
		"estadoCivil":"Soltero",
		"direccion":"Calle Federico Moore 140",
		"fechaNacimiento":"1996-12-13",
		"email":"brian.orellana@unmsm.edu.pe",
		"fechaInscripcion":" "				//en el set está configurado para que se la fecha de Hoy
	}*/
	
	@PutMapping("/pacientes")
	public ResponseEntity<Paciente> updatePaciente(@PathVariable(value="id") String idPaciente,
			@Valid @RequestBody Paciente changes) {
		
		Paciente pa = pacienteService.obtenerPaciente(idPaciente);
		if (pa.equals(null))
			return ResponseEntity.notFound().build();

		//pa.setNombres(changes.getNombres());

		//Paciente updatePaciente = pacienteService.guardarPaciente(pa);

		return ResponseEntity.ok().body(pa);
	}
}
