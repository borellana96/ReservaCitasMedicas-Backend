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
import com.clinica.model.Horario;
import com.clinica.model.Paciente;
import com.clinica.service.IHorarioService;

@RestController
@RequestMapping("/clinica")
public class HorarioController {
	
	@Autowired
	private IHorarioService horarioService;

	@GetMapping("/horarios")
	public List<Horario> listar() {
		return horarioService.listarHorarios();
	}
	
	@GetMapping("/horarios/medico/{id}")
	public List<Horario> obtenerHorarioxMedico(@PathVariable(value = "id") int idMedico) {
		return horarioService.obtenerHorarioxMedico(idMedico);
	}
	
	@GetMapping("/horarios/{id}")
	public ResponseEntity<Horario> obtenerHorario(@PathVariable(value = "id") int idHorario) {
		Horario h = horarioService.obtenerHorario(idHorario);
		if (h.equals(null))
			return ResponseEntity.notFound().build();

		return ResponseEntity.ok().body(h);
	}
	
	@PostMapping("/horarios/list")
	public List<Horario> guardarHorarios(@RequestBody List<Horario> horarios) {
		return horarioService.guardarHorarios(horarios);
	}
	//EN POSTMAN:
	/*[
		{
			"idHorario":"50",
			"dia":"Domingo",
			"horaInicio":"13:00:00",
			"horaFin":"15:00:00",
			"medico_id":{
				"idMedico":"2"
			}
		},
		{
			"idHorario":"60",
			"dia":"Domingo",
			"horaInicio":"13:00:00",
			"horaFin":"15:00:00",
			"medico_id":{
				"idMedico":"2"
			}
		},
		{
			"idHorario":70,
			"dia":"Domingo",
			"horaInicio":"13:00:00",
			"horaFin":"15:00:00",
			"medico_id":{
				"idMedico":2
			}
		}
	]*/
	
	@PostMapping("/horarios")
	public Horario guardarHorario(@RequestBody Horario es) {
		return horarioService.guardarHorario(es);
	}
	//EN POSTMAN:
	/*{
		"idHorario":5,		//Se puede omitir porque es Autoincrementable
		"dia":"Domingo",
		"horaInicio":"13:00:00",
		"horaFin":"15:00:00",
		"medido_id":{
			idMedico:2
		}
	}*/
	
	@PutMapping("/horarios")
	public ResponseEntity<Horario> updateHorario(@PathVariable(value = "id") int idHorario,
			@Valid @RequestBody Paciente changes) {
		
		Horario h = horarioService.obtenerHorario(idHorario);
		if (h.equals(null))
			return ResponseEntity.notFound().build();

		return ResponseEntity.ok().body(h);
	}
}
