package br.com.fabrisio.guideme.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public abstract class AbstractTreatment {
    private String exception;
    private String message;
    private String method;
    private String path;


    protected AbstractTreatment(String exception, String message, String method, String path) {
        this.exception = exception;
        this.message = message;
        this.method = method;
        this.path = path;
    }
}
