package br.com.fabrisio.guideme.dto.login;

import br.com.fabrisio.guideme.dto.user.UserDTO;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {

    private String token;
    private String refreshToken;
    private UserDTO.Login user;

}
