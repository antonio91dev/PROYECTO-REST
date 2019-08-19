package com.control.personal.empresa.modelo;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The Class Horario.
 */
public class Horario {

	/** The id. */
	@Id @GeneratedValue
	private long id;

	/** The ingreso date. */
	@Temporal(TemporalType.DATE)
	private Date ingresoDate;

	/** The salida date. */
	@Temporal(TemporalType.DATE)
	private Date salidaDate;

	/** The registro date. */
	@Temporal(TemporalType.DATE)
	private Date registroDate;

}
