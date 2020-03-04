package com.clinica.service;

import java.util.List;
import com.clinica.model.Horario;

public interface IHorarioService{
	
	public List<Horario> obtenerHorarioxMedico(int id);
	public List<Horario> listarHorarios();
	public Horario obtenerHorario(int id);		
	public Horario guardarHorario(Horario h);
	public List<Horario> guardarHorarios(List<Horario> h);
}
