package br.com.fabrisio.guideme.security;

import br.com.fabrisio.guideme.entity.user.UserEntity;
import br.com.fabrisio.guideme.exception.AuthenticationException;
import br.com.fabrisio.guideme.exception.NotFoundException;
import br.com.fabrisio.guideme.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity usuario = findUserByEmail(username);
        if (Objects.isNull(usuario)) {
            usuario = findUserByUsername(username);
        }
        if (Objects.isNull(usuario)) {
            throw new UsernameNotFoundException("Usuário não encontrado com e-mail informado");
        }
        return new AuthUser(usuario);
    }

    public AuthUser userLogged() {
        try {
            return (AuthUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            throw new AuthenticationException(e.getMessage(), e);
        }
    }

    private UserEntity findUserByEmail(String email) {
        try {
            return userService
                    .findByEmail(email);
        } catch(NotFoundException e) {
            return null;
        }
    }

    private UserEntity findUserByUsername(String username) {
        try {
            return userService
                    .findByUsername(username);
        } catch(NotFoundException e) {
            return null;
        }
    }
}
