package br.com.fabrisio.guideme.service;

import br.com.fabrisio.guideme.dto.UserDto;
import br.com.fabrisio.guideme.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    UserEntity create(UserDto userDto);
    UserEntity read(Long id);
    UserEntity update(UserDto userDto);
    UserEntity delete(Long id);
    List<UserEntity> list();
    Page<UserEntity> pageable(Pageable pageable);
    UserEntity resetPassword(UserDto userDto);

}
