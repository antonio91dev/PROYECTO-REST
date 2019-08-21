package com.control.personal.empresa.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.control.personal.empresa.modelo.Persona;

/**
 * The Interface EmpleadoRepository.
 */
public interface PersonaRepository extends JpaRepository<Persona, Long>{
	
	Persona findPersonaByPis(String pis);
	Boolean existsByPis(String pis);
	
	
}
