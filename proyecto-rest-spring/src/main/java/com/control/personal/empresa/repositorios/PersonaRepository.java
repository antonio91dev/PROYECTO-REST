package com.control.personal.empresa.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.control.personal.empresa.modelo.Empleado;

/**
 * The Interface EmpleadoRepository.
 */
public interface EmpleadoRepository extends JpaRepository<Empleado, Long>{
	
	/**
	 * Find empleado by pis.
	 *
	 * @param pis the pis
	 * @return the empleado
	 */
	Empleado findEmpleadoByPis(String pis);
	
}
