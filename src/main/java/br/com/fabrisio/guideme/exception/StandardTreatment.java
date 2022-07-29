package br.com.fabrisio.guideme.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class StandardTreatment extends AbstractTreatment {
    private List<String> stack;
    private String type;


    public StandardTreatment(List<String> stack, String type, String exception, String message, String method, String path) {
        super(exception, message, method, path);
        this.stack = stack;
        this.type = type;
    }
}
