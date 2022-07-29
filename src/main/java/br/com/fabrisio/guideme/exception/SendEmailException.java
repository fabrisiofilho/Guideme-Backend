package br.com.fabrisio.guideme.exception;

public class SendEmailException extends RuntimeException{

    private static final long serialVersionUID = 1l;

    public SendEmailException(String message, Throwable cause){
        super(message, cause);
    }

    public SendEmailException(String message){
        super(message);
    }

}
