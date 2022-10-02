package br.com.fabrisio.guideme.service.impl;

import br.com.fabrisio.guideme.dto.user.UserProgressDTO;
import br.com.fabrisio.guideme.entity.roadmap.StepEntity;
import br.com.fabrisio.guideme.entity.user.UserEntity;
import br.com.fabrisio.guideme.entity.user.UserProgressEntity;
import br.com.fabrisio.guideme.exception.NotFoundException;
import br.com.fabrisio.guideme.repository.UserProgressRepository;
import br.com.fabrisio.guideme.repository.UserRepository;
import br.com.fabrisio.guideme.service.StepService;
import br.com.fabrisio.guideme.service.UserProgressService;
import br.com.fabrisio.guideme.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserProgressServiceImpl implements UserProgressService {

    @Autowired
    private UserProgressRepository userProgressRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StepService stepService;

    @Override
    public UserProgressEntity create(UserProgressDTO dto) {
        UserEntity userEntity = userService.read(dto.getUserId());
        StepEntity stepEntity = stepService.read(dto.getStepId());
        UserProgressEntity userProgressEntity = UserProgressEntity.builder()
                .step(stepEntity)
                .user(userEntity)
                .isOpen(dto.isOpen())
                .isDone(dto.isDone())
                .build();

        userEntity.setCoins(userEntity.getCoins() + stepEntity.getBountyCoin());
        userEntity.setExps(userEntity.getExps() + stepEntity.getBountyXp());

        userRepository.save(userEntity);

        return userProgressRepository.save(userProgressEntity);
    }

    @Override
    public UserProgressEntity read(Long id) {
        return userProgressRepository.findById(id).orElseThrow(()->{throw new NotFoundException("Não foi encontrado o progresso do usuario");});
    }

    @Override
    public UserProgressEntity update(UserProgressDTO dto) {
        return userProgressRepository.save(modelMapper.map(dto, UserProgressEntity.class));
    }

    @Override
    public UserProgressEntity delete(Long id) {
        UserProgressEntity userProgressEntity = read(id);
        userProgressRepository.deleteById(id);
        return userProgressEntity;
    }

    @Override
    public List<UserProgressEntity> findByUser(UserEntity user) {
        return userProgressRepository.findByUser(user.getId());
    }
}
