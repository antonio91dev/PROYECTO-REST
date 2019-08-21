package com.control.personal.empresa.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.control.personal.empresa.modelo.Empleado;
import com.control.personal.empresa.repositorios.EmpleadoRepository;

/**
 * The Class EmpleadoServicio.
 */
@Service
public class EmpleadoServicio {
	
	@Autowired
	EmpleadoRepository repositorio;
	
	/**
	 * Registrar.
	 *
	 * @param e the e
	 * @return the empleado
	 */
	public Empleado registrar(Empleado e) {
		return repositorio.save(e);
	}
	
	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the empleado
	 */
	public Empleado findById(long id) {
		return repositorio.findById(id).orElse(null);
	}
	
	/**
	 * Buscar por email.
	 *
	 * @param pis the pis
	 * @return the empleado
	 */
	public Empleado buscarPorEmail(String pis) {
		return repositorio.findEmpleadoByPis(pis);
	}
	
	

}
