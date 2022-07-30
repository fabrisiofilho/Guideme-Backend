package br.com.fabrisio.guideme.security.util;

import br.com.fabrisio.guideme.configuration.EnvironmentVariable;
import br.com.fabrisio.guideme.exception.TokenException;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;


@Component
public class TokenJWTSecurity {

    private static final String SECRET = EnvironmentVariable.TOKEN_SECRET_PHRASE;
    private static final Long EXPIRATION = EnvironmentVariable.TOKEN_EXPIRATION;
    private static final Long EXPIRATION_REFRESH_TOKEN = EnvironmentVariable.EXPIRATION_REFRESH_TOKEN;

    public String generateToken(String username) {
        return Jwts.builder().setSubject(username).setExpiration(new Date(System.currentTimeMillis() + EXPIRATION)).signWith(SignatureAlgorithm.HS512, SECRET.getBytes()).compact();
    }

    public String generateRefreshToken(String username) {
        return Jwts.builder().setSubject(username).setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_REFRESH_TOKEN)).signWith(SignatureAlgorithm.HS512, SECRET.getBytes()).compact();
    }

    public boolean tokenValid(String token) {
        var claims = getClaims(token);
        if (claims != null) {
            String username = claims.getSubject();
            var expirationDate = claims.getExpiration();
            var nowDate = new Date(System.currentTimeMillis());

            return username != null && expirationDate != null && nowDate.before(expirationDate);
        }
        return false;
    }

    private Claims getClaims(String token) {
        try {
            return Jwts.parser().setSigningKey(SECRET.getBytes()).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            throw new TokenException(e.getMessage(), e);
        }
    }

    public String getUserEmail(String token) {
        var claims = getClaims(token);
        if (claims != null) {
            return claims.getSubject();
        }
        return null;
    }


}
