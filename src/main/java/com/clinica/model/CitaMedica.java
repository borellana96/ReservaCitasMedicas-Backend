package com.clinica.model;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name="CITA_MEDICA")
public class CitaMedica {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int idCita;
	
	private String estado;
	
	private boolean estadoPago;
	
	@JoinColumn(name = "paciente_id", nullable = false)
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Paciente paciente_id;
	
	@JoinColumn(name = "medico_id", nullable = false)
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Medico medico_id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	
	private String tipoPago;
	
	public int getIdCita() {
		return idCita;
	}
	public void setIdCita(int idCita) {
		this.idCita = idCita;
	}
	
	public Paciente getPaciente_id() {
		return paciente_id;
	}
	public void setPaciente_id(Paciente paciente_id) {
		this.paciente_id = paciente_id;
	}
	
	public Medico getMedico_id() {
		return medico_id;
	}
	public void setMedico_id(Medico medico_id) {
		this.medico_id = medico_id;
	}

	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = "Proceso";
	}
	
	public boolean isEstadoPago() {
		return estadoPago;
	}
	public void setEstadoPago(boolean estadoPago) {
		this.estadoPago = estadoPago;
	}
	
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public String getTipoPago() {
		return tipoPago;
	}
	public void setTipoPago(String tipoPago) {
		this.tipoPago = tipoPago;
	}

	
}
