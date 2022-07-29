package br.com.fabrisio.guideme.security;

import br.com.fabrisio.guideme.handlers.ServiceExceptionsHandler;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class BasicEntryPointSecurity implements AuthenticationEntryPoint {


    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        ServiceExceptionsHandler.handler(request, response, authException, HttpStatus.UNAUTHORIZED);
    }
}
