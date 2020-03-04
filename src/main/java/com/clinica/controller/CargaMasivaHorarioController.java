package com.clinica.controller;

import java.util.List;

import com.clinica.model.ResultadoCargaMasivaHorario;
import com.clinica.service.ICargaMasivaHorarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class CargaMasivaHorarioController {
	
	@Autowired
	private ICargaMasivaHorarioService CargaMasivaHorarioService;
	
	@PostMapping(value = "/carga-masivaor/horario")
	public List<ResultadoCargaMasivaHorario> cargarArchivoVisa(@RequestParam("file[]") List<MultipartFile> file){
		return this.CargaMasivaHorarioService.cargarArchivos(file);
	}
	
	
}
