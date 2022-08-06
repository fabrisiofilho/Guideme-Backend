package br.com.fabrisio.guideme.security.filter;

import br.com.fabrisio.guideme.configuration.constant.HeadersConstants;
import br.com.fabrisio.guideme.configuration.context.GuidemeContext;
import br.com.fabrisio.guideme.entity.user.UserEntity;
import br.com.fabrisio.guideme.handlers.ServiceExceptionsHandler;
import br.com.fabrisio.guideme.security.CustomUserDetailsService;
import br.com.fabrisio.guideme.security.util.TokenJWTSecurity;
import br.com.fabrisio.guideme.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.AntPathMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class AuthorizationFilter extends BasicAuthenticationFilter {

    private final TokenJWTSecurity jwtSecurity;
    private final UserService userService;
    private final CustomUserDetailsService CustomUserDetailsService;

    private static final String[] URL_IGNORE = { "/auth/**" };

    public AuthorizationFilter(AuthenticationManager authenticationManager, TokenJWTSecurity jwtSecurity,
                               UserService userService, CustomUserDetailsService customUserDetailsService) {
        super(authenticationManager);
        this.jwtSecurity = jwtSecurity;
        this.userService = userService;
        CustomUserDetailsService = customUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(HeadersConstants.X_AUTHORIZATION);

        try {
            if (Objects.nonNull(header)) {
                UsernamePasswordAuthenticationToken token = getAuthentication(header);
                if (token != null) {
                    SecurityContextHolder.getContext().setAuthentication(token);
                }
            }
            chain.doFilter(request, response);
        } catch (Exception exception) {
            SecurityContextHolder.clearContext();
            ServiceExceptionsHandler.handler(request, response, exception, HttpStatus.UNAUTHORIZED);
        }

    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        AntPathMatcher pathMatcher = new AntPathMatcher();
        return Arrays.asList(URL_IGNORE).stream().anyMatch(p -> pathMatcher.match(p, request.getRequestURI()));
    }

    @Override
    protected boolean isIgnoreFailure() {
        return true;
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String token) {
        if (jwtSecurity.tokenValid(token)) {
            String email = jwtSecurity.getUserEmail(token);
            UserDetails userDetails = CustomUserDetailsService.loadUserByUsername(email);
            UserEntity user = userService.findByEmail(email);
            GuidemeContext.setCurrentUser(user);
            return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        }
        return null;
    }

}
