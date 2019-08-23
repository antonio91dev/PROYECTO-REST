package com.control.personal.empresa.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.control.personal.empresa.modelo.Persona;
import com.control.personal.empresa.modelo.dto.PersonaDto;


public interface PersonaRepository extends JpaRepository<Persona, Long> {

	Persona findPersonaByPis(String pis);

	Boolean existsByPis(String pis);
	
	@Query("SELECT new com.control.personal.empresa.modelo.dto.PersonaDto"
			+ "(d.pis, DATE_FORMAT(e.ingresoDate,'%d - %b - %Y') , DATE_FORMAT(e.salidaDate,'%d - %b - %Y'),  e.minutosGanados) "
			+ "FROM Persona d INNER JOIN d.horario e WHERE d.pis=?1 ")
	List<PersonaDto> findDetailALLByPis(String pis);

	@Query("SELECT new com.control.personal.empresa.modelo.dto.PersonaDto"
			+ "(d.pis, DATE_FORMAT(e.ingresoDate,'%d - %b - %Y'),  SUM(e.minutosGanados)) "
			+ "FROM Persona d INNER JOIN d.horario e WHERE d.pis=?1 "
			+ "GROUP BY DATE_FORMAT(e.ingresoDate,'%d - %b - %Y'),d.id "
			+ "ORDER BY d.pis,DATE_FORMAT(e.ingresoDate,'%d - %b - %Y')")
	List<PersonaDto> findDetailDiaByPis(String pis);

	@Query("SELECT new com.control.personal.empresa.modelo.dto.PersonaDto"
			+ "(d.pis, DATE_FORMAT(e.ingresoDate,'%b - %Y'),  SUM(e.minutosGanados)) "
			+ "FROM Persona d INNER JOIN d.horario e WHERE d.pis=?1 "
			+ "GROUP BY DATE_FORMAT(e.ingresoDate,'%b - %Y'),d.id "
			+ "ORDER BY d.pis,DATE_FORMAT(e.ingresoDate,'%b - %Y')")
	List<PersonaDto> findDetailMesByPis(String pis);

}
