package br.com.fabrisio.guideme.security.core.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {

    private String usuario;

    private String nome;

    private String email;

    private String senha;

    private String senhaNovamente;

}
