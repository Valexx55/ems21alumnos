package edu.kyndryl.academy.msalumnos.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * ESTA CLASE VA A SER COMO UN "LISTENER" / SUMIDERO DE EXCEPCIONES CUALQUIER
 * FALLO EN NUESTRO CÓDIGO, PODEMOS GESTIONARLO AQUÍ
 */
@RestControllerAdvice(basePackages = { "edu.kyndryl.academy.msalumnosprofe" }) // estoy escuchando los fallos de este
																				// paquete
public class GestionExcepciones {
	
	Logger logger = LoggerFactory.getLogger(GestionExcepciones.class);//dame un log para esta clase

	// PARA CADA FALLO, PUEDE DEFINIR UN MÉTODO QUE SERÁ INVOCADO AL OCURRI ESE
	// FALLO
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<String> gestionNullPointer(NullPointerException excepcion) {
		ResponseEntity<String> responseEntity = null;

			String mensaje_error = excepcion.getMessage();
			responseEntity = ResponseEntity.internalServerError().body(mensaje_error);
			logger.error(mensaje_error, excepcion);

		return responseEntity;
	}

	// PARA un FALLO GENÉRICO, inesperado, ponemos este método por defecto
	@ExceptionHandler(Throwable.class)
	public ResponseEntity<String> gestionExcepcionRara(Throwable excepcion) {
		ResponseEntity<String> responseEntity = null;

			String mensaje_error = excepcion.getMessage();
			responseEntity = ResponseEntity.internalServerError().body(mensaje_error);
			logger.error(mensaje_error, excepcion);

		return responseEntity;
	}

}
