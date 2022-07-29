package br.com.fabrisio.guideme.configuration;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EnvironmentVariable {

    public static final String ENVIRONMENT = System.getenv("ENVIRONMENT");

    public static final String TOKEN_SECRET_PHRASE = System.getenv("TOKEN_SECRET_PHRASE");
    public static final Long TOKEN_EXPIRATION = Long.valueOf(System.getenv("TOKEN_EXPIRATION"));
    public static final Long EXPIRATION_REFRESH_TOKEN = Long.valueOf(System.getenv("EXPIRATION_REFRESH_TOKEN"));

}
