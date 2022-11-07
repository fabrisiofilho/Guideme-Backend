package br.com.fabrisio.guideme.service.impl;

import br.com.fabrisio.guideme.configuration.context.GuidemeContext;
import br.com.fabrisio.guideme.dto.challenger.ChallengerDTO;
import br.com.fabrisio.guideme.dto.challenger.ValidateChallengerDTO;
import br.com.fabrisio.guideme.entity.challenger.ChallengerEntity;
import br.com.fabrisio.guideme.entity.challenger.UserConclusionChallengerEntity;
import br.com.fabrisio.guideme.entity.user.UserEntity;
import br.com.fabrisio.guideme.exception.BuninessException;
import br.com.fabrisio.guideme.repository.ChallengerRepository;
import br.com.fabrisio.guideme.repository.UserConclusionChallengerRepository;
import br.com.fabrisio.guideme.repository.UserRepository;
import br.com.fabrisio.guideme.service.ChallengerService;
import br.com.fabrisio.guideme.service.NotificationService;
import br.com.fabrisio.guideme.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ChallengerServiceImpl implements ChallengerService {

    @Autowired
    private ChallengerRepository challengerRepository;

    @Autowired
    private UserService  userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private UserConclusionChallengerRepository userConclusionChallengerRepository;

    @Override
    public ChallengerEntity createChallenger(ChallengerDTO itemDTO) {
        notificationService.create("Novo desafio cadastrado.");
        return challengerRepository.save(modelMapper.map(itemDTO, ChallengerEntity.class));
    }

    @Override
    public ChallengerEntity readChallenger(Long id) {
        return challengerRepository.findById(id).orElse(null);
    }

    @Override
    public ChallengerEntity updateChallenger(ChallengerDTO itemDTO) {
        return challengerRepository.save(modelMapper.map(itemDTO, ChallengerEntity.class));
    }

    @Override
    public ChallengerEntity deleteChallenger(Long id) {
        ChallengerEntity challenger = readChallenger(id);
        challengerRepository.deleteById(id);
        return challenger;
    }

    @Override
    public List<ChallengerEntity> listChallengers() {
        return challengerRepository.findAll();
    }

    @Override
    public Page<ChallengerEntity> pageableChallengers(Pageable pageable) {
        return challengerRepository.findAll(pageable);
    }

    @Override
    public Page<ChallengerEntity> seachChallengers(String title, Pageable pageable) {
        var listItem = userConclusionChallengerRepository.findByUserChallenger(GuidemeContext.getCurrentUser().getId());
        if (listItem.isEmpty()) {
            return challengerRepository.search(title, pageable);
        }
        return challengerRepository.search(title, pageable, listItem);
    }

    @Override
    public UserConclusionChallengerEntity validatedChallenger(ValidateChallengerDTO validateChallengerDTO) {
        ChallengerEntity challenger = readChallenger(validateChallengerDTO.getId());
        UserEntity userEntity = GuidemeContext.getCurrentUser();


        if (!challenger.getResult().trim().equals(validateChallengerDTO.getResponse().trim())) {
            var ucce = UserConclusionChallengerEntity.builder()
                    .challengerEntity(challenger)
                    .user(userEntity)
                    .isDone(false)
                    .build();
            userConclusionChallengerRepository.save(ucce);
            throw new BuninessException("Resposta está incorreta.");
        }


        var ucce = UserConclusionChallengerEntity.builder()
                .challengerEntity(challenger)
                .user(userEntity)
                .isDone(true)
                .build();

        userEntity.setCoins(userEntity.getCoins() + challenger.getBountyCoin());
        userEntity.setExps(userEntity.getExps() + challenger.getBountyXp());
        userEntity.setPoints(userEntity.getPoints() + challenger.getPoints());

        userRepository.save(userEntity);

        return userConclusionChallengerRepository.save(ucce);
    }

    @Override
    public List<ChallengerEntity> findByUser() {
        var uccr = userConclusionChallengerRepository.findByUser(GuidemeContext.getCurrentUser().getId());

        List<ChallengerEntity>  challengerEntities = new ArrayList<>();

        uccr.forEach(userConclusionChallengerEntity -> {
            ChallengerEntity challengerEntity = userConclusionChallengerEntity.getChallengerEntity();
            challengerEntity.setIsDone(userConclusionChallengerEntity.getIsDone());
            challengerEntities.add(challengerEntity);
        });

        return challengerEntities;

    }
}
