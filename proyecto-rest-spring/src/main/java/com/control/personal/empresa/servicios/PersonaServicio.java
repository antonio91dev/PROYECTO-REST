package com.control.personal.empresa.servicios;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.control.personal.empresa.modelo.Horario;
import com.control.personal.empresa.modelo.Persona;
import com.control.personal.empresa.repositorios.PersonaRepository;

/**
 * The Class EmpleadoServicio.
 */
@Service
public class PersonaServicio {

	@Autowired
	PersonaRepository repositorio;

	@Autowired
	HorarioServicio horarioServicio;

	public Persona registrar(Persona e) {
		return repositorio.save(e);
	}

	public Persona registrarIngreso(Persona persona,Horario horario ) {
		
		horario.setPersona(persona);
		persona.getHorario().add(horario);
		return repositorio.save(persona);

	}

	public Persona buscarPorId(long id) {
		return repositorio.findById(id).orElse(null);
	}

	public Persona buscarPersonaPorPIS(String pis) {
		return repositorio.findPersonaByPis(pis);
	}

	public Boolean existePIS(String pis) {
		return repositorio.existsByPis(pis);
	}
	

}
