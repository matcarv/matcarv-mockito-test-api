package com.matcarv.app.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Tratamento de exceções do microserviço.
 * 
 * @author Weslley Matos
 */
@ControllerAdvice
public class MicroserviceExceptionHandler {

    /**
     * Tratamento de exceções de requisição inválida.
     * 
     * @param e the exception
     * @return ResponseEntity with error details
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleBadRequestException(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Erro de requisição inválida: " + e.getMessage());
    }

    /**
	 * Tratamento de exceções de validação.
     * 
	 * @param e the exception
	 * @return ResponseEntity with error details
	 */
	@ExceptionHandler({ MethodArgumentNotValidException.class })
	public ResponseEntity<?> handleException(final MethodArgumentNotValidException ex) {
		return ResponseEntity
                .status(ex.getStatusCode())
                .body(ex.getBindingResult()
                		.getAllErrors()
                		.stream().map(e -> { 
                			return e.getDefaultMessage(); 
                		})
                	);
	 }
}
