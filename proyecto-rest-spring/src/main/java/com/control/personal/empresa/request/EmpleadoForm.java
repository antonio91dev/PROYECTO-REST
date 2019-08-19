package com.control.personal.empresa.request;

import javax.validation.constraints.NotBlank;

public class EmpleadoForm {

	@NotBlank	
	 private String pis;

	 @NotBlank
	 private String dateTime;

	public String getPis() {
		return pis;
	}

	public void setPis(String pis) {
		this.pis = pis;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	 
}
