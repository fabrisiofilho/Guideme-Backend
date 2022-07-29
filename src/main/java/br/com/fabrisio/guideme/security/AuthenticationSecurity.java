package br.com.fabrisio.guideme.security;

import br.com.fabrisio.guideme.dto.UserDTO;
import br.com.fabrisio.guideme.handlers.ServiceExceptionsHandler;
import br.com.fabrisio.guideme.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class AuthenticationSecurity extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final TokenJWTSecurity jwtSecurity;
    private final UserService userService;


    public AuthenticationSecurity(AuthenticationManager authenticationManager, TokenJWTSecurity jwtSecurity, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtSecurity = jwtSecurity;
        this.userService = userService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            UserDTO.Credential credenciaisDTO = new ObjectMapper().readValue(request.getInputStream(), UserDTO.Credential.class);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(credenciaisDTO.getEmail(), credenciaisDTO.getPassword(), new ArrayList<>());
            return authenticationManager.authenticate(authenticationToken);
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) {
        Long id = ((AuthUser) authResult.getPrincipal()).getId();
        String username = ((AuthUser) authResult.getPrincipal()).getUsername();
        String token = jwtSecurity.generateToken(username);
        String refreshToken = jwtSecurity.generateRefreshToken(username);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {
        ServiceExceptionsHandler.handler(request, response, failed, HttpStatus.FORBIDDEN);
    }
}

