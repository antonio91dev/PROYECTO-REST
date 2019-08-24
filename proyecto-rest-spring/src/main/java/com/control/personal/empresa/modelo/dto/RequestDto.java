package com.control.personal.empresa.modelo.dto;

import javax.validation.constraints.NotBlank;

public class RequestDto {

	@NotBlank
	private String pis;

	@NotBlank
	private String dateTime;

	
	public RequestDto() {
    }
	
	
	
	public RequestDto(@NotBlank String pis) {
		super();
		this.pis = pis;
	}



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
	
	public static RequestDto toDto(String pis) {
        return new RequestDto(pis);
    }

}
