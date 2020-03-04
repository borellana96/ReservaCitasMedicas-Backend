package com.clinica.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity
@Table(name = "USUARIO")
public class Usuario {

	@Id
	@Column(name = "dni", length = 8)
	@Size(min = 8, max = 8, message="El DNI debe contener 8 caracteres")
	private String dni;

	@Column(name = "password", length = 1000)
	@NotNull
	private String password;

	@NotNull
	private String perfil;

	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getPerfil() {
		return perfil;
	}
	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

}
