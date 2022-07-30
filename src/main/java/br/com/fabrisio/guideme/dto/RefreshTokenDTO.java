package br.com.fabrisio.guideme.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RefreshTokenDTO {

    private String token;
    private String refreshToken;

}
