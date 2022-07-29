package br.com.fabrisio.guideme.util;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("guideme.email")
public class EmailProperties {

    @NotNull
    private String remetente;

}