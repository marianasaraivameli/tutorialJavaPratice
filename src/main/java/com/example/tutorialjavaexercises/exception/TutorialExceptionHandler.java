package com.example.tutorialjavaexercises.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.InvalidPropertiesFormatException;

@ControllerAdvice
public class TutorialExceptionHandler {
    @ExceptionHandler(InvalidParamException.class)
    public ResponseEntity<ExceptionDetails> handlerInvalidParamException(InvalidParamException ex) {
        return new ResponseEntity<>(
                ExceptionDetails.builder()
                        .title("Parametro inválido")
                        .message(ex.getMessage())
//                        .timestamp(LocalDateTime.now())
                        .timestamp("Verificar este passo no repositorio")
                        .build(),
                    HttpStatus.BAD_REQUEST
        );

    }

}
