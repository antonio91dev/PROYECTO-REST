package com.control.personal.empresa.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.control.personal.empresa.modelo.Horario;
import com.control.personal.empresa.modelo.Persona;

public interface HorarioRepository extends JpaRepository<Horario, Long> {

	Horario findByPersonaAndEstado(Persona p, int e);

	Boolean existsByPersonaAndEstado(Persona p, int e);

//	@Query("SELECT new com.jeejava.dto.DeptEmpDto(d.name, e.name, e.email, e.address) "
//			+ "FROM Department d LEFT JOIN d.employees e")
//	List<DeptEmpDto> fetchEmpDeptDataLeftJoin();

//	@Query("FROM Persona")
//	List<Persona> groupBy();
//	
//	Query query = em.createQuery(
//  		  "select c "
//  		+ "from Persona c "
//  		+ "left join fetch c.horario ");

//	@Query("FROM ReleaseDateType AS rdt LEFT JOIN rdt.cacheMedias AS cm WHERE cm.id = ?1")    //This is using a named query method
//

//	@Query("select new com.demo.entities.PersonaGroup(p.category.id as categoryId, "
//			+ "min(p.price) as minPrice, "
//			+ "max(p.price) as maxPrice, "
//			+ "sum(p.quantity) as sumQuantity, "
//			+ "count(p.id) as countProduct, "
//			+ "avg(p.price) as avgPrice) "
//			+ "from Horario p "
//			+ "group by p.category.id")
//	public List<PersonaGroup> groupBy();
}

//
//SELECT DATE_FORMAT(ingresoDate,'%d - %b - %Y') as fecha ,sum(minutosGanados),COUNT(persona_id) ,persona_id 
//FROM PROYECTOREST.Horario
//group by DATE_FORMAT(ingresoDate,'%d - %b - %Y') ,persona_id