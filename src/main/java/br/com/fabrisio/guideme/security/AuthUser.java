package br.com.fabrisio.guideme.security;

import br.com.fabrisio.guideme.entity.UserEntity;
import lombok.Getter;
import org.springframework.security.core.userdetails.User;

import java.util.Collections;

@Getter
public class AuthUser extends User {

    private final String name;
    private final String email;

    public AuthUser(UserEntity user){
        super(user.getUsername(), user.getPassword(), Collections.emptyList());
        this.name = user.getUsername();
        this.email = user.getEmail();
    }

}