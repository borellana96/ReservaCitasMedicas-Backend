package com.clinica.model;

import java.sql.Time;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name="HORARIO")
public class Horario {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int idHorario;
	
	private String dia;
	
	@NotNull
	private Time horaInicio;

	@NotNull
	private Time horaFin;

	@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="medico_id", nullable = false)
	private Medico medico_id;
	
	public int getIdHorario() {
		return idHorario;
	}
	public void setIdHorario(int idHorario) {
		this.idHorario = idHorario;
	}

	public String getDia() {
		return dia;
	}
	public void setDia(String dia) {
		this.dia = dia;
	}
	
	public Time getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(Time horaInicio) {
		this.horaInicio = horaInicio;
	}
	
	public Time getHoraFin() {
		return horaFin;
	}
	public void setHoraFin(Time horaFin) {
		this.horaFin = horaFin;
	}
	
	public Medico getMedico_id() {
		return medico_id;
	}	
	public void setMedico_id(Medico medico_id) {
		this.medico_id = medico_id;
	}
	
}
