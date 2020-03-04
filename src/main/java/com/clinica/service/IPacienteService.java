package com.clinica.service;

import java.util.List;

import com.clinica.model.Paciente;

public interface IPacienteService {
	
	public Paciente obtenerPaciente(String id);	
	public List<Paciente> listarPacientes();	
	public Paciente guardarPaciente(Paciente p);
}
