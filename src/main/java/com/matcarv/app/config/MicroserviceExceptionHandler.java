package com.matcarv.app.config;

import java.util.List;

import org.springframework.context.MessageSourceResolvable;
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
    public ResponseEntity<String> handleBadRequestException(final Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Erro de requisição inválida: " + e.getMessage());
    }

    /**
	 * Tratamento de exceções de validação.
     * 
	 * @param ex the exception
	 * @return ResponseEntity with error details
	 */
    @ExceptionHandler({ MethodArgumentNotValidException.class })
    public ResponseEntity<List<String>> handleException(final MethodArgumentNotValidException ex) {
        return ResponseEntity
                .status(ex.getStatusCode())
                .body(ex.getBindingResult()
                        .getAllErrors()
                        .stream()
                        .map(MessageSourceResolvable::getDefaultMessage)
                        .toList()
                    );
     }
}
