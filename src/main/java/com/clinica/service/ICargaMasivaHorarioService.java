package com.clinica.service;

import java.util.List;

import com.clinica.model.ResultadoCargaMasivaHorario;

import org.springframework.web.multipart.MultipartFile;


public interface ICargaMasivaHorarioService {
	
	List<ResultadoCargaMasivaHorario> cargarArchivos(List<MultipartFile> multipartfiles);
	

}
