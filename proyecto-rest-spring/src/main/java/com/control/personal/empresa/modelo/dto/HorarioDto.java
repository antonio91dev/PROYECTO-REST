package com.control.personal.empresa.modelo.dto;

import java.time.LocalDateTime;

public class HorarioDto {

	
	private LocalDateTime salidaDate;
	
	
	
	
	public HorarioDto(LocalDateTime salidaDate) {
		this.salidaDate = salidaDate;
	}

	public LocalDateTime getSalidaDate() {
		return salidaDate;
	}

	public void setSalidaDate(LocalDateTime salidaDate) {
		this.salidaDate = salidaDate;
	}

	
	
}
