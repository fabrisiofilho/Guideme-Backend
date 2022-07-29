package br.com.fabrisio.guideme.exception;

public class AuthenticationException extends RuntimeException {

    public AuthenticationException(String message) {
        super(message);
    }

    public AuthenticationException(String message, Exception e) {
        super(message, e);
    }
}

