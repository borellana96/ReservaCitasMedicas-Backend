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
import com.clinica.model.Especialidad;
import com.clinica.service.IEspecialidadService;

@RestController
@RequestMapping("/clinica")
public class EspecialidadController {

	@Autowired
	private IEspecialidadService especialidadService;

	@GetMapping("/especialidades")
	public List<Especialidad> listar() {
		return especialidadService.listarEspecialidades();
	}

	@GetMapping("/especialidades/{id}")
	public ResponseEntity<Especialidad> obtenerEspecialidad(@PathVariable(value = "id") int idEspecialidad) {
		Especialidad esp = especialidadService.obtenerEspecialidad(idEspecialidad);
		if (esp.equals(null))
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok().body(esp);
	}

	@PostMapping("/especialidades")
	public Especialidad guardarEspecialidad(@RequestBody Especialidad es) {
		return especialidadService.guardarEspecialidad(es);
	}
	//EN POSTMAN:
	/*{
		"idEspecialidad":7,		//Se puede omitir porque es Autoincrementable
		"nombre":"Otorrino",
		"descripcion":"Trata sobre el oido",
		"numConsultorio":2,
		"imageURL": "http://www.imagen.jpg"
	}*/
	
	
	@PutMapping("/especialidades")
	public ResponseEntity<Especialidad> updatePaciente(@PathVariable(value="id") int idEspecialidad,
			@Valid @RequestBody Especialidad changes) {
		
		Especialidad pa = especialidadService.obtenerEspecialidad(idEspecialidad);
		if (pa.equals(null))
			return ResponseEntity.notFound().build();

		return ResponseEntity.ok().body(pa);
	}
}
