package com.clinica.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.clinica.model.Medico;
import com.clinica.service.IMedicoService;

@RestController
@RequestMapping("/clinica")
public class MedicoController {

	@Autowired
	private IMedicoService medicoService;

	@GetMapping("/medicos/especialidad/{id}")
	public List<Medico> obtenerMedicoxEspecialidad(@PathVariable(value = "id") int idEspecialidad) {
		return medicoService.obtenerMedicoxEspecialidad(idEspecialidad);
	}

	@GetMapping("/medicos/{id}")
	public ResponseEntity<Medico> obtenerMedico(@PathVariable(value = "id") int idMedico) {
		Medico med = medicoService.obtenerMedico(idMedico);
		if (med.equals(null))
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok().body(med);
	}

	@GetMapping("/medicos")
	public List<Medico> listarMedicos() {
		return medicoService.listarMedicos();
	}

	@PostMapping("/medicos")
	public Medico guardarMedico(@RequestBody Medico me) {	
		return medicoService.guardarMedico(me);
	}
	//EN POSTMAN:
	/*{
		"idMedico": 1,		//Se puede omitir porque es Autoincrementable
		"nombres":"Andrea",
		"apellidoP":"Tapia",
		"apellidoM":"Caceres",
		"especialidad_id":{
			"idEspecialidad":3
		}
	}*/
	
}
