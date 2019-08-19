/*
 * 
 */
package com.control.personal.empresa.controladores;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.control.personal.empresa.request.EmpleadoForm;
import com.control.personal.empresa.response.ResponseData;

/**
 * En el Class EmpleadoController que recibe los parametros de ..InterviewClient
 * 
 */
@RestController
@RequestMapping("/api/clockin")
public class EmpleadoController {

	
	/**
	 * Create.
	 *
	 * @param empleadoRequest the empleado request
	 * @return the response data
	 */
	@PostMapping("/create")
	public @ResponseBody ResponseData create(@RequestBody EmpleadoForm empleadoRequest) {
		ResponseData response = new ResponseData();
		
		System.out.println("Obteniendo Empleado " + empleadoRequest.getPis());
		System.out.println("Obteniendo Hora/Registro" + empleadoRequest.getDateTime());
		return response;
	}

}
