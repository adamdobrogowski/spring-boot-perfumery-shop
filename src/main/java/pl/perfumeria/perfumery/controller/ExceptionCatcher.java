package pl.perfumeria.perfumery.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public class ExceptionCatcher {
    @ExceptionHandler(exception = HttpClientErrorException.NotFound.class)
    public String notFound() {
        return "error/404";
    }

    @ExceptionHandler(exception = Exception.class)
    public ResponseEntity<String> otherExceptions() {
        return ResponseEntity.internalServerError().build();
    }
}
