package com.clinica.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name="PACIENTE")
public class Paciente {

	@Id
	@Column(name="dni", length=8)
	@Size(min=8, max=8)
	private String dni;
	
	@Column(name="nombres", length=40)
	@NotNull
	private String nombres;
	
	@Column(name="apellido_paterno", length=20)
	@NotNull
	private String apellidoPaterno;
	
	@Column(name="apellido_materno", length=20)
	@NotNull
	private String apellidoMaterno;
	
	@Column(name="sexo", length=9)
	@NotNull
	private String sexo;
	
	@Column(name="estadoCivil", length=20)
	@NotNull
	private String estadoCivil;
	
	@Column(name="direccion", length=60)
	@NotNull
	private String direccion;
	
	@Temporal(TemporalType.DATE)
	private Date fechaNacimiento;
	
	@Email
	private String email;
	
	@Temporal(TemporalType.DATE)
	private Date fechaInscripcion;

	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
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

	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}
	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public Date getFechaInscripcion() {
		return fechaInscripcion;
	}
	public void setFechaInscripcion(String fecha) {
		Date fechaActual = new Date();
		this.fechaInscripcion = fechaActual;
	}

}
