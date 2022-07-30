package br.com.fabrisio.guideme.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {

    private String token;
    private String refreshToken;
    private UserDTO user;

}
