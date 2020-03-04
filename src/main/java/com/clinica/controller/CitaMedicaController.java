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
import com.clinica.model.CitaMedica;
import com.clinica.service.CitaMedicaService;

@RestController
@RequestMapping("/clinica")
public class CitaMedicaController {

	@Autowired
	private CitaMedicaService citaMedicaService;
	
	@GetMapping("/citas")
	public List<CitaMedica> listar() {
		return citaMedicaService.listarCitas();
	}

	@GetMapping("/citas/paciente/{dni}")
	public List<CitaMedica> obtenerCitaMedicaxDni(@PathVariable(value = "dni") String dni) {
		return citaMedicaService.obtenerCitaxDni(dni);
	}
	
	@GetMapping("/citas/{id}")
	public ResponseEntity<CitaMedica> obtenerCitaMedica(@PathVariable(value = "id") int idCitaMedica) {
		CitaMedica cita = citaMedicaService.obtenerCita(idCitaMedica);
		if (cita.equals(null))
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok().body(cita);
	}

	@PostMapping("/citas")
	public CitaMedica guardarCita(@RequestBody CitaMedica cita) {
		return citaMedicaService.guardarCita(cita);
	}
	//EN POSTMAN:
	/*{
		"idCita":2,	
		"estado":"Finalizado",		//Por default es en proceso
		"paciente_id":{
			"dni": "73524246"
		},
		"medico_id":{
			"idMedico": "2"
		},
		"fecha":"2019-12-20T10:30:00",
		"tipoPago":"presencial"
	}*/
		
	@PutMapping("/citas")
	public ResponseEntity<CitaMedica> updateCita(@PathVariable(value="id") int idCitaMedica,
			@Valid @RequestBody CitaMedica changes) {
		
		CitaMedica cita = citaMedicaService.obtenerCita(idCitaMedica);
		if (cita.equals(null))
			return ResponseEntity.notFound().build();

		return ResponseEntity.ok().body(cita);
	}
}
