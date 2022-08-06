package br.com.fabrisio.guideme.util.sender;

import lombok.Builder;
import lombok.Getter;

public interface SendMailService {

    void enviar(Mensagem mensagem);

    @Getter
    @Builder
    class Mensagem{

        private String destinatarios;
        private String assunto;
        private String corpo;

    }

}