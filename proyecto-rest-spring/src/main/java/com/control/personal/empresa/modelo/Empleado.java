package com.control.personal.empresa.modelo;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

/**
 * The Class Empleado.
 */
@Entity
public class Empleado {

	
	@Id @GeneratedValue
	private long id;
	
	@Column(nullable=false)
	@NotEmpty
	private String pis;
	
	private String nombre;
	
	//@Temporal(TemporalType.DATE)
	private LocalDate registroDate;
	
	
	/**
	 * Instantiates a new empleado.
	 */
	public Empleado() {
		
	}


	/**
	 * Instantiates a new empleado.
	 *
	 * @param pis the pis
	 * @param nombre the nombre
	 * @param registroDate the registro date
	 */
	public Empleado(@NotEmpty String pis, String nombre, LocalDate registroDate) {
		this.pis = pis;
		this.nombre = nombre;
		this.registroDate = registroDate;
	}


	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public long getId() {
		return id;
	}


	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(long id) {
		this.id = id;
	}


	/**
	 * Gets the pis.
	 *
	 * @return the pis
	 */
	public String getPis() {
		return pis;
	}


	/**
	 * Sets the pis.
	 *
	 * @param pis the new pis
	 */
	public void setPis(String pis) {
		this.pis = pis;
	}


	/**
	 * Gets the nombre.
	 *
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}


	/**
	 * Sets the nombre.
	 *
	 * @param nombre the new nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	/**
	 * Gets the registro date.
	 *
	 * @return the registro date
	 */
	public LocalDate getRegistroDate() {
		return registroDate;
	}


	/**
	 * Sets the registro date.
	 *
	 * @param registroDate the new registro date
	 */
	public void setRegistroDate(LocalDate registroDate) {
		this.registroDate = registroDate;
	}
	
	
}
