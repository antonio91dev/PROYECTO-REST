package com.control.personal.empresa.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.control.personal.empresa.modelo.Horario;
import com.control.personal.empresa.modelo.Persona;

public interface HorarioRepository extends JpaRepository<Horario, Long>{
	
	Horario findByPersonaAndEstado(Persona persona,int e);
	
	Boolean existsByPersonaAndEstado(Persona p ,int e);
	
}
