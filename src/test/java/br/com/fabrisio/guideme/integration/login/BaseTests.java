package br.com.fabrisio.guideme.integration.login;

import br.com.fabrisio.guideme.service.UserService;
import br.com.fabrisio.guideme.service.impl.UserServiceImpl;
import br.com.fabrisio.guideme.util.firebase.FirebaseBlobStorage;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

abstract class BaseTests {

    @TestConfiguration
    static class TestConfig{

        @Bean
        UserService userService() {
            return new UserServiceImpl();
        }

        @Bean
        PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Bean
        FirebaseBlobStorage firebaseBlobStorage() {
            return Mockito.mock(FirebaseBlobStorage.class);
        }

    }

}
