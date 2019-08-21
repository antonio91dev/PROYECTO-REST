/*
 * 
 */
package com.control.personal.empresa.controladores;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.control.personal.empresa.modelo.Horario;
import com.control.personal.empresa.modelo.Persona;
import com.control.personal.empresa.request.PersonaForm;
import com.control.personal.empresa.response.ResponseData;
import com.control.personal.empresa.servicios.HorarioServicio;
import com.control.personal.empresa.servicios.PersonaServicio;
import com.control.personal.empresa.servicios.Utilitarios;

@RestController
@RequestMapping("/api/clockin")
public class PersonaController {

	@Autowired
	private PersonaServicio personaServicio;

	@Autowired
	private HorarioServicio horarioServicio;

	private Logger _log = LoggerFactory.getLogger(PersonaController.class);

	@PostMapping("/create")
	public @ResponseBody ResponseData create(@RequestBody PersonaForm personaRequest) {
		ResponseData response = new ResponseData();
		_log.info(ZoneId.systemDefault().toString());
		LocalDateTime fechaRegistro = Utilitarios.parseFecha(personaRequest.getDateTime());

		if (personaServicio.existePIS(personaRequest.getPis())) {

			Persona persona = personaServicio.buscarPersonaPorPIS(personaRequest.getPis());
			persona.setRegistroDate(fechaRegistro);
			Horario horario = new Horario();

			if (horarioServicio.existsByPersonaAndEstado(persona, 0)) {
				
				horario = horarioServicio.findByPersonaAndEstado(persona, 0);
				horario.setSalidaDate(fechaRegistro);
				horario.setEstado(1);
				horario.setMinutosGanados(Utilitarios.obtenerMinutosRango(horario.getIngresoDate(), horario.getSalidaDate()));

			} else {
				horario.setIngresoDate(fechaRegistro);
				horario.setEstado(0);
			}
			personaServicio.registrarIngreso(persona, horario);

		} else {
			Persona persona = new Persona(personaRequest.getPis(), fechaRegistro);
			Horario horario = new Horario(fechaRegistro, 0);
			personaServicio.registrarIngreso(persona, horario);

		}

		return response;
	}

}
