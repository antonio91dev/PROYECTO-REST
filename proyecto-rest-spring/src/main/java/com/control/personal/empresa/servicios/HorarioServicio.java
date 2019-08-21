package com.control.personal.empresa.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.control.personal.empresa.modelo.Horario;
import com.control.personal.empresa.modelo.Persona;
import com.control.personal.empresa.repositorios.HorarioRepository;

/**
 * The Class EmpleadoServicio.
 */
@Service
public class HorarioServicio {
	
	@Autowired
	HorarioRepository repositorio;
	
	
	public Horario registrar(Horario e) {
		return repositorio.save(e);
	}
	
	public Horario findByPersonaAndEstado(Persona p ,int e) {
		return repositorio.findByPersonaAndEstado(p , e);
	}
	public Boolean existsByPersonaAndEstado(Persona p ,int e) {
		return repositorio.existsByPersonaAndEstado(p , e);
	}
	
	
}
