package com.clinica.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name="MEDICO")
public class Medico {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int idMedico;
	
	@Column(name="nombres")
	private String nombres;
	
	@Column(name="apellido_paterno")
	private String apellidoPaterno;
	
	@Column(name="apellido_materno")
	private String apellidoMaterno;
	
	@Column(name="imageURL", length=1000)
	private String imageURL; 
	
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="especialidad_id", nullable = false)
	private Especialidad especialidad_id;
    
	public int getIdMedico() {
		return idMedico;
	}
	public void setIdMedico(int idMedico) {
		this.idMedico = idMedico;
	}

	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	
	public Especialidad getEspecialidad_id() {
		return especialidad_id;
	}
	public void setEspecialidad_id(Especialidad especialidad_id) {
		this.especialidad_id = especialidad_id;
	}
	
}
