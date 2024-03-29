package com.control.personal.empresa.config.test;

import static org.junit.Assert.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
public class BasicAuthenticationIntegrationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void logeoUsur() throws Exception {
		this.mockMvc.perform(get("/").with(httpBasic("user5", "password"))).andExpect(authenticated());
	}

	@Test
	public void accesoProtegido() throws Exception {

		this.mockMvc.perform(get("/")).andExpect(status().isUnauthorized());

	}

	@Test
	public void loginInvalidUser() throws Exception {
		MvcResult result = this.mockMvc.perform(formLogin().user("invalid").password("invalid"))
				.andExpect(unauthenticated()).andExpect(status().is4xxClientError()).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains("HTTP Status 401"));
	}
}
