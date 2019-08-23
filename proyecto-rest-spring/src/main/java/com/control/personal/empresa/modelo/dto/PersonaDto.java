package com.control.personal.empresa.modelo.dto;

public class PersonaDto {

	private String personaPis;
	private String fechaIngreso;
	private String salidaDate;
	private long minutosTrabajados;
	private String horasDescanso;

	public PersonaDto(String personaPis, String fechaIngreso, long minutosTrabajados) {
		this.personaPis = personaPis;
		this.fechaIngreso = fechaIngreso;
		this.minutosTrabajados = minutosTrabajados;
	}

	

	public PersonaDto(String personaPis, String fechaIngreso, String salidaDate, long minutosTrabajados) {
		this.personaPis = personaPis;
		this.fechaIngreso = fechaIngreso;
		this.salidaDate = salidaDate;
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
	
	public String getHorasDescanso() {
		
		int hours = (int) (getMinutosTrabajados() / 60);
		int minutes = (int) (getMinutosTrabajados() % 60);
		horasDescanso = hours + ":" +minutes ;
		if(hours > 4 & hours < 6) {
			horasDescanso = horasDescanso  + " é necessário um descanso \n" + 
					"mínimo de 15 minutos";
		}else if(hours > 6) {
			horasDescanso = horasDescanso  + " é necessário um descanso mínimo de 1 hora.";
		}else if(hours <= 4) {
			horasDescanso =  horasDescanso  +  " não é necessário descanso";
		}
		
		
		return horasDescanso;
	}

	public void setHorasDescanso(String horasDescanso) {
		this.horasDescanso = horasDescanso;
	}



	public String getSalidaDate() {
		return salidaDate;
	}



	public void setSalidaDate(String salidaDate) {
		this.salidaDate = salidaDate;
	}

	
	
	
}
