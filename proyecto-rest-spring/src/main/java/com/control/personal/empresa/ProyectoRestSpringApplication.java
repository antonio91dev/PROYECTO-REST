package com.control.personal.empresa;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.control.personal.empresa.repositorios.EmpleadoRepository;

@SpringBootApplication
public class ProyectoRestSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoRestSpringApplication.class, args);
	}
	
	
	/**
	 * Este bean se inicia al lanzar la aplicación. 
	 * 
	 * @param empleadoRepository 
	 * @return
	 */
	@Bean
    CommandLineRunner init(EmpleadoRepository empleadoRepository) {
        return (args) -> {
            //Empleado empleado = new Empleado("123123","nombre 213123", LocalDate.now());
            //empleadoRepository.save(empleado);
        };
    }

}
