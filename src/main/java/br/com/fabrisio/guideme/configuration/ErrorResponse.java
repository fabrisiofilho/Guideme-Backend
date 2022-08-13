package br.com.fabrisio.guideme.configuration;

import br.com.fabrisio.guideme.configuration.constant.ResponseValues;
import br.com.fabrisio.guideme.exception.AbstractTreatment;
import br.com.fabrisio.guideme.exception.StandardTreatment;
import br.com.fabrisio.guideme.util.TraceUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ErrorResponse {

    public static ResponseEntity<Map<String, List<AbstractTreatment>>> handle(List<AbstractTreatment> treatments, HttpStatus status) {
        Map<String, List<AbstractTreatment>> result = new HashMap<>();
        result.put(ResponseValues.KEY_ERRORS, treatments);
        return new ResponseEntity<>(result, status);
    }

    public static ResponseEntity<Map<String, AbstractTreatment>> handle(Exception exception, HttpServletRequest request, HttpStatus status) {
        Map<String, AbstractTreatment> result = new HashMap<>();
        var treatment = createStandardTreatment(exception, request);

        result.put(ResponseValues.KEY_ERRORS, treatment);
        return new ResponseEntity<>(result, status);
    }

    public static Map<String, AbstractTreatment> handle(Exception exception, HttpServletRequest request) {
        var treatment = createStandardTreatment(exception, request);
        HashMap<String, AbstractTreatment> result = new HashMap<>();
        result.put(ResponseValues.KEY_ERRORS, treatment);
        return result;
    }

    private static AbstractTreatment createStandardTreatment(Exception exception, HttpServletRequest request) {
        var stack = TraceUtil.filterTrace(List.of(exception.getStackTrace()));
        var exceptionType = exception.getClass().getSimpleName();
        var errorMessage = TraceUtil.catchRootException(exception).getMessage();
        var restMethod = request.getMethod();
        var restUri = request.getRequestURI();
        return new StandardTreatment(stack, ResponseValues.KEY_GLOBAL, exceptionType, errorMessage, restMethod, restUri);
    }
}

