package br.com.fabrisio.guideme.handlers;

import br.com.fabrisio.guideme.configuration.ErrorResponse;
import br.com.fabrisio.guideme.exception.AbstractTreatment;
import br.com.fabrisio.guideme.exception.TokenException;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ServiceExceptionsHandler {

    public static void handler(HttpServletRequest request, HttpServletResponse response, TokenException e, HttpStatus status) throws IOException {
        summary(request, response, e, status);
    }

    public static void handler(HttpServletRequest request, HttpServletResponse response, AuthenticationException e, HttpStatus status) throws IOException {
        summary(request, response, e, status);
    }

    public static void handler(HttpServletRequest request, HttpServletResponse response, Exception e, HttpStatus status) throws IOException {
        summary(request, response, e, status);
    }

    private static void summary(HttpServletRequest request, HttpServletResponse response, Exception e, HttpStatus status) throws IOException {
        Map<String, AbstractTreatment> map = ErrorResponse.handle(e, request);
        var jsonObject = new JSONObject(map);

        response.setStatus(status.value());
        response.setContentType("application/json");
        response.getWriter().append(jsonObject.toString());
    }
}

