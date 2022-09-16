package br.com.fabrisio.guideme.service.impl;

import br.com.fabrisio.guideme.dto.user.UserProgressDTO;
import br.com.fabrisio.guideme.entity.user.UserEntity;
import br.com.fabrisio.guideme.entity.user.UserProgressEntity;
import br.com.fabrisio.guideme.exception.NotFoundException;
import br.com.fabrisio.guideme.repository.UserProgressRepository;
import br.com.fabrisio.guideme.service.UserProgressService;
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

    @Override
    public UserProgressEntity create(UserProgressDTO dto) {
        return userProgressRepository.save(modelMapper.map(dto, UserProgressEntity.class));
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
