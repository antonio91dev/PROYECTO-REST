/**
 * 
 */
package com.control.personal.empresa.controladores;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.control.personal.empresa.servicios.PersonaServicio;


@RunWith(SpringRunner.class)
@WebMvcTest(value = PersonaController.class)
public class PersonaControllerTest {
	
	
	 @Autowired
	   private MockMvc mvc;
	   @MockBean
	   private PersonaController personaController;


//	   @Test
//	   public void getArrivalsById() throws Exception {
//	       Arrival arrival = new Arrival();
//	       arrival.setCity("Yerevan");
//	       given(arrivalController.getArrivalById(arrival.getId())).willReturn(arrival);
//	       mvc.perform(get(VERSION + ARRIVAL + arrival.getId())
//	               .with(user("blaze").password("Q1w2e3r4"))
//	               .contentType(APPLICATION_JSON))
//	               .andExpect(status().isOk())
//	               .andExpect(jsonPath("city", is(arrival.getCity())));
//	   }
	   
}
