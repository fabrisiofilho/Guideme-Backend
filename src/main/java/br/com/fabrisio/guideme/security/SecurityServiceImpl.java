package br.com.fabrisio.guideme.security;

import br.com.fabrisio.guideme.entity.user.UserEntity;
import br.com.fabrisio.guideme.security.util.TokenJWTSecurity;
import br.com.fabrisio.guideme.service.UserService;
import br.com.fabrisio.guideme.util.sender.SendMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl {

    @Autowired
    private SendMailService sendGrid;

    @Autowired
    private UserService  userService;

    @Autowired
    private TokenJWTSecurity tokenJWTSecurity;

    public void sendEmailRevocer(String email) {
        UserEntity userEntity = userService.findByEmail(email);
        var user = userService.updateRecoverToken(tokenJWTSecurity.generateToken(userEntity.getUsername()), userEntity);
        var link = "http://localhost:4200/resetPassword?token=" + user.getTokenRecover();

        sendGrid.enviar(SendMailService.Mensagem.builder()
                .assunto("Solicitação para redefinição de senha.")
                .corpo(getHtmlTemplate(link, user))
                .destinatarios(email)
                .build());
    }

    private String getHtmlTemplate(String link, UserEntity userEntity) {
        StringBuilder signature = new StringBuilder();

        signature.append("Prezado, ");
        signature.append(userEntity.getName());
        signature.append("<br>");
        signature.append("Foi solicitado uma redefinição de senha, segui o link:");
        signature.append("<br>");
        signature.append(link);

        return signature.toString();

    }

}
