package br.com.fabrisio.guideme.integration.auth;

import br.com.fabrisio.guideme.dto.user.UserDTO;
import br.com.fabrisio.guideme.entity.user.ProfileEnum;
import br.com.fabrisio.guideme.entity.user.UserEntity;
import br.com.fabrisio.guideme.exception.NotFoundException;
import br.com.fabrisio.guideme.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@ActiveProfiles("test")
@DataJpaTest
@RunWith(SpringRunner.class)
@SpringJUnitConfig(BaseTests.TestConfig.class)
@Transactional
public class AuthTest {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("Teste para encontrar usuario com email, as email não tem relação nenhuma com nenhum usuario.")
    public void testNotFoundUserbyEmail() {
        Assert.assertThrows(NotFoundException.class, () -> {
            userService.findByEmail("nothing@email.com");
        });
    }

    @Test
    @DisplayName("Registrar usuario e validar se está correto o dados dentro do banco.")
    public void testRegisterUser() {
        String email = "test-1@gmail.com";
        String senha = "123";

        var repositoryUser = userService.create(UserDTO.builder()
                                .email(email)
                                .username("test")
                                .name("test 1")
                                .password("123")
                                .build());

        var user = userService.findByEmail(repositoryUser.getEmail());
        Assert.assertEquals(ProfileEnum.ALUNO, user.getProfile());
        Assert.assertNotNull(user.getInventory());
        Assert.assertTrue(passwordEncoder.matches(senha, user.getPassword()));
    }


}
