package br.com.fabrisio.guideme.integration.service.roadmap;

import br.com.fabrisio.guideme.configuration.context.GuidemeContext;
import br.com.fabrisio.guideme.dto.user.UserDTO;
import br.com.fabrisio.guideme.entity.user.UserEntity;
import br.com.fabrisio.guideme.service.RoadmapService;
import br.com.fabrisio.guideme.service.UserService;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@ActiveProfiles("test")
@DataJpaTest
@RunWith(SpringRunner.class)
@SpringJUnitConfig(BaseTests.TestConfig.class)
@Transactional
public class RoadmapTest {

    @Autowired
    private RoadmapService roadmapService;

    @Autowired
    private UserService userService;


    @Test
    @DisplayName("Buscar o Roadmap do Usuario.")
    public void testFindRoadmap() {
        var repositoryUser = userService.create(UserDTO.builder()
                .email("email@gmail.com")
                .username("test")
                .name("test 1")
                .password("123")
                .build());
        GuidemeContext.setCurrentUser(repositoryUser);
        roadmapService.getRoadmapByUserProgress();

    }


}
