package br.com.fabrisio.guideme.integration.service.roadmap;

import br.com.fabrisio.guideme.service.RoadmapService;
import br.com.fabrisio.guideme.service.UserProgressService;
import br.com.fabrisio.guideme.service.UserService;
import br.com.fabrisio.guideme.service.impl.RoadmapServiceImpl;
import br.com.fabrisio.guideme.service.impl.UserProgressServiceImpl;
import br.com.fabrisio.guideme.service.impl.UserServiceImpl;
import br.com.fabrisio.guideme.util.firebase.FirebaseBlobStorage;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

abstract class BaseTests {

    @TestConfiguration
    static class TestConfig{

        @Bean
        RoadmapService roadmapService() {
            return new RoadmapServiceImpl();
        }

        @Bean
        UserProgressService userProgressService() {
            return new UserProgressServiceImpl();
        }

        @Bean
        ModelMapper modelMapper() {
            return new ModelMapper();
        }

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
