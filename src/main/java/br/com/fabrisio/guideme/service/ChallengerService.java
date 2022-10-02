package br.com.fabrisio.guideme.service;

import br.com.fabrisio.guideme.dto.challenger.ChallengerDTO;
import br.com.fabrisio.guideme.dto.challenger.ValidateChallengerDTO;
import br.com.fabrisio.guideme.entity.challenger.ChallengerEntity;
import br.com.fabrisio.guideme.entity.challenger.UserConclusionChallengerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ChallengerService {

    ChallengerEntity createChallenger(ChallengerDTO itemDTO);
    ChallengerEntity readChallenger(Long id);
    ChallengerEntity updateChallenger(ChallengerDTO itemDTO);
    ChallengerEntity deleteChallenger(Long id);
    List<ChallengerEntity> listChallengers();
    Page<ChallengerEntity> pageableChallengers(Pageable pageable);
    Page<ChallengerEntity> seachChallengers(String title, Pageable pageable);

    UserConclusionChallengerEntity validatedChallenger(ValidateChallengerDTO validateChallengerDTO);

    List<ChallengerEntity> findByUser();

}
