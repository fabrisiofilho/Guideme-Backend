package br.com.fabrisio.guideme.integration.login;

import br.com.fabrisio.guideme.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringRunner;

@ActiveProfiles("test")
@DataJpaTest
@RunWith(SpringRunner.class)
@SpringJUnitConfig(BaseTests.TestConfig.class)
public class LoginTest {

    @Autowired
    private UserService userService;

    @Test
    public void name() {
        userService.findByEmail("");
    }

}
