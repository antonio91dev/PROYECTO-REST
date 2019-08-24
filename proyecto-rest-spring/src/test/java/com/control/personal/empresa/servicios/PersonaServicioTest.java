package com.control.personal.empresa.servicios;

import static org.junit.Assert.assertThat;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.control.personal.empresa.modelo.Horario;
import com.control.personal.empresa.modelo.Persona;
import com.control.personal.empresa.repositorios.PersonaRepository;



@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonaServicioTest {
	
	
	@Autowired
	private PersonaServicio personaService;
	
	@MockBean
	private PersonaRepository personaRepository;

	@Test
	public void testCreatePersona() {
		
		Persona p = new Persona();
		p.setPis("");
		p.setRegistroDate(LocalDateTime.now());
		
		Horario h = new Horario();
		h.setIngresoDate(LocalDateTime.now());
		h.setEstado(0);
		
	    Mockito.when(personaRepository.save(p)).thenReturn(p);

	}
	
	
	@Test
	public void testfindPersonaByPis(){
		Persona p = new Persona();
	    Mockito.when(personaRepository.findPersonaByPis("")).thenReturn(p);
	}

}
