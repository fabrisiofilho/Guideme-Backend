package br.com.fabrisio.guideme.service;

import br.com.fabrisio.guideme.dto.user.UserProgressDTO;
import br.com.fabrisio.guideme.entity.user.UserEntity;
import br.com.fabrisio.guideme.entity.user.UserProgressEntity;

import java.util.List;

public interface UserProgressService {

    UserProgressEntity create(UserProgressDTO dto);
    UserProgressEntity read(Long id);
    UserProgressEntity update(UserProgressDTO dto);
    UserProgressEntity delete(Long id);
    List<UserProgressEntity> findByUser(UserEntity user);

}
