package br.com.fabrisio.guideme.configuration;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public class SuccessResponse<T> {

    public ResponseEntity<T> handle(T object, Class<?> classController, HttpServletRequest request, HttpStatus status) {
        return new ResponseEntity<>(object, status);
    }

}
