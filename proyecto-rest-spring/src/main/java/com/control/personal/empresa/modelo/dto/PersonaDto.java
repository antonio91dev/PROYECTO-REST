package com.control.personal.empresa.modelo.dto;

public class PersonaDto {

	private String personaPis;
	private String fechaIngreso;
	private long minutosTrabajados;
	
	public PersonaDto(String personaPis, String fechaIngreso, long minutosTrabajados) {
		this.personaPis = personaPis;
		this.fechaIngreso = fechaIngreso;
		this.minutosTrabajados = minutosTrabajados;
	}
	public String getPersonaPis() {
		return personaPis;
	}
	public void setPersonaPis(String personaPis) {
		this.personaPis = personaPis;
	}
	public String getFechaIngreso() {
		return fechaIngreso;
	}
	public void setFechaIngreso(String fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	public long getMinutosTrabajados() {
		return minutosTrabajados;
	}
	public void setMinutosTrabajados(long minutosTrabajados) {
		this.minutosTrabajados = minutosTrabajados;
	}

	
	
}
