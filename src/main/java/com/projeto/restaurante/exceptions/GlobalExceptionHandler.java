package com.projeto.restaurante.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({AttendantAlreadyRegisteredExceptoin.class})
    public ResponseEntity<Object> handleAttendantAlreadyRegisteredExceptoin(AttendantAlreadyRegisteredExceptoin ex) {
        return new ResponseEntity(ex.paraJson(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler({UnregisteredAttendantExceptoin.class})
    public ResponseEntity<Object> handleUnregisteredAttendantExceptoin(UnregisteredAttendantExceptoin ex) {
        return new ResponseEntity(ex.paraJson(), HttpStatus.NOT_FOUND);
    }


    // Captura erros gerais de sistema
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, Object> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        });

        // Cria uma instância de ErrorResponse
        ErrorResponse errorResponse = new ErrorResponse("Erro de validação", 400);
        errors.forEach((field, message) -> errorResponse.paraJson().put(field, message));
        return ResponseEntity.badRequest().body(errorResponse.paraJson());
    }
}