package com.matcarv.app.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Tratamento de exceções do microserviço.
 */
@ControllerAdvice
public class MicroserviceExceptionHandler {

    /**
     * Tratamento de exceções de requisição inválida.
     * 
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleBadRequestException(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Erro de requisição inválida: " + e.getMessage());
    }

    /**
	 * Tratamento de exceções de validação.
     * 
	 * @param e
	 * @return
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
