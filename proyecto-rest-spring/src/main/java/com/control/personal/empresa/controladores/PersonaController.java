/*
 * 
 */
package com.control.personal.empresa.controladores;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.control.personal.empresa.modelo.Horario;
import com.control.personal.empresa.modelo.Persona;
import com.control.personal.empresa.modelo.dto.ErrorResponseDto;
import com.control.personal.empresa.modelo.dto.HorarioDto;
import com.control.personal.empresa.modelo.dto.PersonaDto;
import com.control.personal.empresa.modelo.dto.RequestDto;
import com.control.personal.empresa.servicios.HorarioServicio;
import com.control.personal.empresa.servicios.PersonaServicio;
import com.control.personal.empresa.servicios.Utilitarios;

@RestController
@RequestMapping("/api/clockin")
public class PersonaController {

	@Autowired
	private PersonaServicio personaServicio;

	@Autowired
	private HorarioServicio horarioServicio;

	private Logger log = LoggerFactory.getLogger(PersonaController.class);

	@PostMapping(path = "/create")
	public ResponseEntity<?> create(@RequestBody RequestDto requestDto) {

//		ErrorResponseDto error = new ErrorResponseDto("Not found", "Courier not found");
//        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
		try {
			LocalDateTime fechaRegistro = Utilitarios.parseFecha(requestDto.getDateTime());
			String fechaActual = Utilitarios.formatFecha(LocalDateTime.now(), "dd-MM-yyyy");

			if (personaServicio.existsByPis(requestDto.getPis())) {

				Persona persona = personaServicio.findPersonaByPis(requestDto.getPis());
				persona.setRegistroDate(fechaRegistro);
				Horario horario = new Horario();

				if (horarioServicio.existsByPersonaAndEstado(persona, 0)) {

					horario = horarioServicio.findByPersonaAndEstado(persona, 0);

					if (Utilitarios.validarEntreDosFechas(horario.getIngresoDate(), fechaRegistro)) {
						horario.setSalidaDate(fechaRegistro);
						horario.setEstado(1);
						long minutos = Utilitarios.obtenerminutoRangoIngresoyEntrada(horario.getIngresoDate(),
								fechaRegistro);
						horario.setMinutosGanados(minutos);

						personaServicio.save(persona, horario);
						return new ResponseEntity<>(requestDto, HttpStatus.OK);
					} else {
						log.info("La Fecha de salida es incorrecta");
						ErrorResponseDto error = new ErrorResponseDto("Validacion Fecha", "Fecha Incorrecta");
						return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
					}
				} else {
					HorarioDto d = personaServicio.findValidateDiaByPis(requestDto.getPis());
					if (Utilitarios.validarEntreDosFechas(d.getSalidaDate(),fechaRegistro)) {
						horario.setIngresoDate(fechaRegistro);
						horario.setEstado(0);
						personaServicio.save(persona, horario);
						return new ResponseEntity<>(requestDto, HttpStatus.OK);
					} else {
						log.info("La Fecha de ingreso es incorrecta");
						ErrorResponseDto error = new ErrorResponseDto("Validacion Fecha", "Fecha Incorrecta");
						return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
					}

				}
			} else {
				Persona persona = new Persona(requestDto.getPis(), fechaRegistro);
				Horario horario = new Horario(fechaRegistro, 0);
				personaServicio.save(persona, horario);
				return new ResponseEntity<>(requestDto, HttpStatus.OK);
			}
		} catch (Exception exc) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Foo Not Found", exc);
		}

	}

	@GetMapping(path = "/detail/mes")
	public ResponseEntity<?> getDetailMes(@RequestHeader String pis) {

		if (pis.isEmpty()) {
			ErrorResponseDto error = new ErrorResponseDto("Listado Persona por Mes ", "PIS vacio");
			return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		}

		List<PersonaDto> lstpersona = personaServicio.findDetailMesByPis(pis);
		if (lstpersona.isEmpty()) {
			ErrorResponseDto error = new ErrorResponseDto("Listado Persona por Mes ", "PIS no tiene registro ");
			return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(lstpersona, HttpStatus.OK);
	}

	@GetMapping(path = "/detail/dia")
	public ResponseEntity<?> getDetailDia(@RequestHeader String pis) {

		if (pis.isEmpty()) {
			ErrorResponseDto error = new ErrorResponseDto("Listado Persona por Dia ", "PIS vacio");
			return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		}

		List<PersonaDto> lstpersona = personaServicio.findDetailDiaByPis(pis);
		if (lstpersona.isEmpty()) {
			ErrorResponseDto error = new ErrorResponseDto("Listado Persona por Dia ", "PIS no tiene registro ");
			return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(lstpersona, HttpStatus.OK);
	}

	@GetMapping(path = "/detail/all")
	public ResponseEntity<?> getDetailAll(@RequestHeader String pis) {

		if (pis.isEmpty()) {
			ErrorResponseDto error = new ErrorResponseDto("Listado Persona por All ", "PIS vacio");
			return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		}

		List<PersonaDto> lstpersona = personaServicio.findDetailALLByPis(pis);
		if (lstpersona.isEmpty()) {
			ErrorResponseDto error = new ErrorResponseDto("Listado Persona por All ", "PIS no tiene registro ");
			return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(lstpersona, HttpStatus.OK);
	}

//	@GetMapping("/searchCargo")
//	public List<UsuarioModel> buscarCuentaCargo(
//			@RequestParam(name = "cuenta") String cuenta, 
//			@RequestParam(name = "cargo") int cargo){
//		return serviceUsuario.buscarCuentaCargo(cuenta, cargo);

}
