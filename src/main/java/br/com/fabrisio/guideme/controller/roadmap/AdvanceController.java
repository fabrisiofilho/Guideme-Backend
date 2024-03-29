package br.com.fabrisio.guideme.controller.roadmap;

import br.com.fabrisio.guideme.configuration.context.GuidemeContext;
import br.com.fabrisio.guideme.dto.roadmap.QuestionDTO;
import br.com.fabrisio.guideme.entity.roadmap.LayerEntity;
import br.com.fabrisio.guideme.entity.roadmap.QuestionEntity;
import br.com.fabrisio.guideme.entity.user.UserEntity;
import br.com.fabrisio.guideme.entity.user.UserProgressEntity;
import br.com.fabrisio.guideme.exception.BuninessException;
import br.com.fabrisio.guideme.repository.LayerRepository;
import br.com.fabrisio.guideme.repository.QuestionRepository;
import br.com.fabrisio.guideme.repository.UserProgressRepository;
import br.com.fabrisio.guideme.service.UserProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/advance")
public class AdvanceController {

    @Autowired
    private UserProgressService userProgressService;

    @Autowired
    private UserProgressRepository userProgressRepository;

    @Autowired
    private LayerRepository layerRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @PostMapping("")
    @PreAuthorize("hasAnyAuthority('ALUNO')")
    public void registerProgress(@RequestBody QuestionDTO questionDTO){
        QuestionEntity questionEntity = questionRepository.findById(questionDTO.getId()).orElse(null);
        if (Objects.isNull(questionEntity)) {
            throw new BuninessException("Não foi possivel encontrar questionario");
        }
        if (validateForm(questionEntity, questionDTO)) {
            UserEntity userEntity = GuidemeContext.getCurrentUser();
            var up = userProgressService.findByUserAndStep(userEntity.getId(), questionDTO.getStepId());
            up.setIsDone(true);
            up.setIsOpen(false);
            up = userProgressRepository.save(up);
            validateNextLayer(up, userEntity);
        } else {
            throw new BuninessException("Tente novamente, há respostas incorretas");
        }
    }


    private boolean validateForm(QuestionEntity questionEntity, QuestionDTO questionDTO) {
        if (!questionEntity.getResponseOne().equals(questionDTO.getResponseOne())) {
            return false;
        }
        if (!questionEntity.getResponseTwo().equals(questionDTO.getResponseTwo())) {
            return false;
        }
        if (!questionEntity.getResponseThree().equals(questionDTO.getResponseThree())) {
            return false;
        }
        if (!questionEntity.getResponseFour().equals(questionDTO.getResponseFour())) {
            return false;
        }
        if (!questionEntity.getResponseFive().equals(questionDTO.getResponseFive())) {
            return false;
        }
        return true;
    }


    private void validateNextLayer(UserProgressEntity userProgressEntity, UserEntity userEntity) {
        LayerEntity layer = userProgressEntity.getStep().getLayer();
        var user =  userProgressRepository.faltaStep(layer.getId(), userProgressRepository.findByUserAndStepIsFinally(userEntity.getId()));
        if (user.isEmpty()) {
            var list = layerRepository.findByFirstLayer();
            try {
                var layerOpen = list.get(list.indexOf(layer) + 1);
                layerOpen.getSteps().forEach(it ->
                        userProgressRepository.save(UserProgressEntity.builder()
                                .user(userEntity)
                                .step(it)
                                .isDone(false)
                                .isOpen(true)
                                .build())
                );
            } catch (Exception ignored) {

            }
        }

    }

}
