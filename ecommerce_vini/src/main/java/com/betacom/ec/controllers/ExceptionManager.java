package com.betacom.ec.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.betacom.ec.dto.output.ResponseDTO;
import com.betacom.ec.services.interfaces.IMessaggioService;

public class ExceptionManager {
	
	private IMessaggioService msgS;
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseDTO> handleException(Exception e) {
		return ResponseEntity.badRequest()
				.body(ResponseDTO.builder()
						.msg(msgS.get(e.getMessage()))
						.build());
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDTO> handleValidationException(MethodArgumentNotValidException e) {
          String msg = e.getBindingResult()
                    .getFieldErrors()
                    .stream()
                    .findFirst()
                    .map(FieldError::getDefaultMessage)
                    .orElse("Errore di validazione");

          return ResponseEntity.badRequest()
                    .body(ResponseDTO.builder()
                            .msg(msgS.get(msg))
                            .build()
                            );
          
    }
}
