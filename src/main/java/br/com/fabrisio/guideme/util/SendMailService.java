package br.com.fabrisio.guideme.util;

import lombok.Builder;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public interface SendMailService {

    void enviar(Mensagem mensagem);

    @Getter
    @Builder
    class Mensagem{

        private Set<String> destinatarios;
        private String assunto;
        private String corpo;

    }

}