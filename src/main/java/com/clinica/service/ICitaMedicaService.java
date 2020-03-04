package com.clinica.service;

import java.util.List;
import com.clinica.model.CitaMedica;

public interface ICitaMedicaService {
	
	public List<CitaMedica> obtenerCitaxDni(String dni);
	public CitaMedica obtenerCita(int id);
	public List<CitaMedica> listarCitas();
	public CitaMedica guardarCita(CitaMedica cita);
}
