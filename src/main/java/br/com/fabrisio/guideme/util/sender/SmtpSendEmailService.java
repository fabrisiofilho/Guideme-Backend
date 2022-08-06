package br.com.fabrisio.guideme.util.sender;

import br.com.fabrisio.guideme.exception.SendEmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;

@Component
public class SmtpSendEmailService implements SendMailService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void enviar(Mensagem mensagem) {

        try{
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");

            helper.setFrom("guideme.js@gmail.com");
            helper.setTo(mensagem.getDestinatarios());
            helper.setSubject(mensagem.getAssunto());
            helper.setText(mensagem.getCorpo(), true);

            mailSender.send(mimeMessage);
        }catch(Exception e){
            throw  new SendEmailException("Não foi possivel enviar e-mail", e);
        }

    }

}