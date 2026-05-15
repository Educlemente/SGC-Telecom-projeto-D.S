package br.com.sgc.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeErros {

    // Trata erro 404 (Quando não acha um cliente ou produto no banco)
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarErro404() {
        return ResponseEntity.notFound().build();
    }

    // Trata erro de validação (Quando esquecem de mandar um campo obrigatório)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity tratarErroRegraDeNegocio(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}