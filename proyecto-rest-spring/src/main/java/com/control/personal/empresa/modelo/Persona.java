package com.control.personal.empresa.modelo;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "persona")
public class Persona {

	@Id
	@GeneratedValue
	private long id;

	@Column(nullable = false)
	@NotEmpty
	private String pis;

	@Override
	public String toString() {
		return "Persona [id=" + id + ", pis=" + pis + ", registroDate=" + registroDate + ", horario=" + horario + "]";
	}

	private LocalDateTime registroDate;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "persona")
	private Set<Horario> horario = new HashSet<>();

	public Persona() {

	}

	public Persona(@NotEmpty String pis, LocalDateTime registroDate) {
		this.pis = pis;
		this.registroDate = registroDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPis() {
		return pis;
	}

	public void setPis(String pis) {
		this.pis = pis;
	}

	public LocalDateTime getRegistroDate() {
		return registroDate;
	}

	public void setRegistroDate(LocalDateTime registroDate) {
		this.registroDate = registroDate;
	}

	public Set<Horario> getHorario() {
		return horario;
	}

	public void setHorario(Set<Horario> horario) {
		this.horario = horario;
	}

}
