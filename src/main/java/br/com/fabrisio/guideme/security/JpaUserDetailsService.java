package br.com.fabrisio.guideme.security;

import br.com.fabrisio.guideme.entity.UserEntity;
import br.com.fabrisio.guideme.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity usuario = userRepository
                .findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com e-mail informado"));
        return new AuthUser(usuario);
    }
}
