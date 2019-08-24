package com.control.personal.empresa.modelo;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "horario")
public class Horario {

	@Id
	@GeneratedValue
	private long id;

	private LocalDateTime ingresoDate;

	private LocalDateTime salidaDate;

	private long minutosGanados;

	private int estado;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "persona_id", nullable = false)
	private Persona persona;

	public Horario(LocalDateTime ingresoDate, int estado) {
		this.ingresoDate = ingresoDate;
		this.estado = estado;
	}

	public Horario() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDateTime getIngresoDate() {
		return ingresoDate;
	}

	public void setIngresoDate(LocalDateTime ingresoDate) {
		this.ingresoDate = ingresoDate;
	}

	public LocalDateTime getSalidaDate() {
		return salidaDate;
	}

	public void setSalidaDate(LocalDateTime salidaDate) {
		this.salidaDate = salidaDate;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public long getMinutosGanados() {
		return minutosGanados;
	}

	public void setMinutosGanados(long minutosGanados) {
		this.minutosGanados = minutosGanados;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + estado;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((ingresoDate == null) ? 0 : ingresoDate.hashCode());
		result = prime * result + ((persona == null) ? 0 : persona.hashCode());
		result = prime * result + ((salidaDate == null) ? 0 : salidaDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Horario other = (Horario) obj;
		if (estado != other.estado)
			return false;
		if (id != other.id)
			return false;
		if (ingresoDate == null) {
			if (other.ingresoDate != null)
				return false;
		} else if (!ingresoDate.equals(other.ingresoDate))
			return false;
		if (persona == null) {
			if (other.persona != null)
				return false;
		} else if (!persona.equals(other.persona))
			return false;
		if (salidaDate == null) {
			if (other.salidaDate != null)
				return false;
		} else if (!salidaDate.equals(other.salidaDate))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Horario [id=" + id + ", ingresoDate=" + ingresoDate + ", salidaDate=" + salidaDate + ", estado="
				+ estado + ", persona=" + persona + "]";
	}

}
