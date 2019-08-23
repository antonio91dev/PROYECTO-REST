package com.control.personal.empresa;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.control.personal.empresa.repositorios.PersonaRepository;

@SpringBootApplication
public class ProyectoRestSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoRestSpringApplication.class, args);
	}

	/**
	 * Este bean se inicia al lanzar la aplicación.
	 * 
	 * @param
	 * @return
	 */
	@Bean
	CommandLineRunner init(PersonaRepository personaRepository) {
		return (args) -> {
//			personaRepository.findDetailPerson("12083924837") .forEach((p)-> {
//				System.out.println(p.getEmpAddress());
//				System.out.println(p.getEmpDept());
//				System.out.println(p.getEmpName());
//				});
		};
	}
}
