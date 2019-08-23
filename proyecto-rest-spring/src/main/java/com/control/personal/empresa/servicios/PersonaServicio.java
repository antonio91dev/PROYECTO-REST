package com.control.personal.empresa.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.control.personal.empresa.modelo.Horario;
import com.control.personal.empresa.modelo.Persona;
import com.control.personal.empresa.modelo.dto.PersonaDto;
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

	public Persona save(Persona persona, Horario horario) {

		horario.setPersona(persona);
		persona.getHorario().add(horario);
		return repositorio.save(persona);

	}

	public Persona findById(long id) {
		return repositorio.findById(id).orElse(null);
	}

	public Persona findPersonaByPis(String pis) {
		return repositorio.findPersonaByPis(pis);
	}

	public Boolean existsByPis(String pis) {
		return repositorio.existsByPis(pis);
	}

	public List<PersonaDto> findDetailDiaByPis(String pis) {
		return repositorio.findDetailDiaByPis(pis);
	}

	public List<PersonaDto> findDetailMesByPis(String pis) {
		return repositorio.findDetailMesByPis(pis);
	}

}
