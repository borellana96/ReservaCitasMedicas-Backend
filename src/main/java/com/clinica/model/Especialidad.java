package com.clinica.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name="ESPECIALIDAD")
public class Especialidad {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int idEspecialidad;
	
	@Column(name="nombre", length=50)
	@NotNull
	private String nombre;
	
	@Column(name="descripcion", length=1000)
	private String descripcion;
	
	private int numConsultorio;
	
	private double precio;
	
	@Column(name="imageURL", length=1000)
	private String imageURL;

	public int getIdEspecialidad() {
		return idEspecialidad;
	}
	public void setIdEspecialidad(int idEspecialidad) {
		this.idEspecialidad = idEspecialidad;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public int getNumConsultorio() {
		return numConsultorio;
	}	
	public void setNumConsultorio(int numConsultorio) {
		this.numConsultorio = numConsultorio;
	}
	
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	
}
