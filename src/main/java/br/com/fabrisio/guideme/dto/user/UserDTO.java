package br.com.fabrisio.guideme.dto.user;

import br.com.fabrisio.guideme.entity.user.ProfileEnum;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;

    private String name;

    private String username;

    @javax.validation.constraints.Email(message = "E-mail inválido!")
    private String email;

    private String urlPhoto;

    private String password;

    private ProfileEnum profile;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Credential {
        private String email;
        private String password;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Login {
        private String name;

        private String username;

        private String email;

        private ProfileEnum profile;

        private String urlPhoto;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Register {
        private String name;
        private String email;
        private String password;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UpdatePhoto {
        private Long id;
        private MultipartFile multipartFile;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UpdateName {
        private Long id;
        private String name;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UpdateUser {
        private Long id;
        private String name;
        private String password;
        private String email;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Recover {
        private String email;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ResetPassword {
        private String email;
        private String password;
        private String token;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Email {
        private String email;
    }

}
