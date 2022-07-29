package br.com.fabrisio.guideme.integration.login;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest()
@ActiveProfiles("test")
public class LoginTest {


    @Autowired
    private RestTemplate restTemplate;


}
