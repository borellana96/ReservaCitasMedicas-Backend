package com.clinica.model;

public class ResultadoCargaMasivaHorario {
	String nombreArchivo;
	Boolean exito;
	Integer numeroRegistros;


	public String getNombreArchivo() {
		return nombreArchivo;
	}	
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo= nombreArchivo;
	}
	public void setExito(Boolean exito) {
		this.exito= exito;
	}
	public void setNumeroRegistros(Integer numeroRegistros) {
		this.numeroRegistros= numeroRegistros;
	}

}
