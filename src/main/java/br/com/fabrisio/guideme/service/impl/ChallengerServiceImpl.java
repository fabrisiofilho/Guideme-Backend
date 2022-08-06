package br.com.fabrisio.guideme.service.impl;

import br.com.fabrisio.guideme.dto.challenger.ChallengerDTO;
import br.com.fabrisio.guideme.entity.challenger.ChallengerEntity;
import br.com.fabrisio.guideme.repository.ChallengerRepository;
import br.com.fabrisio.guideme.service.ChallengerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChallengerServiceImpl implements ChallengerService {

    @Autowired
    private ChallengerRepository challengerRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ChallengerEntity createChallenger(ChallengerDTO itemDTO) {
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
        return challengerRepository.search(title, pageable);
    }
}
