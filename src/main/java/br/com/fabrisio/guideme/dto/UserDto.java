package br.com.fabrisio.guideme.dto;

import br.com.fabrisio.guideme.security.core.dto.RegisterDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;

    private String name;

    private String username;

    private String email;

    private String password;

    public UserDto(RegisterDto dto) {
        this.name = dto.getNome();
        this.username = dto.getUsuario();
        this.email = dto.getEmail();
        this.password = dto.getSenha();
    }
}
