package br.com.fabrisio.guideme.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;

    private String name;

    private String username;

    private String email;

    private String password;

    @Getter
    @Setter
    public static class Credential {
        private String email;
        private String password;
    }


}
