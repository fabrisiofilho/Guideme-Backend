package br.com.fabrisio.guideme.service;

import br.com.fabrisio.guideme.dto.UserDTO;
import br.com.fabrisio.guideme.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    UserEntity create(UserDTO userDto);
    UserEntity read(Long id);
    UserEntity update(UserDTO userDto);
    UserEntity delete(Long id);
    List<UserEntity> list();
    Page<UserEntity> pageable(Pageable pageable);
    UserEntity resetPassword(UserDTO userDto);
    Boolean isEmailInUse(String Email);
    UserEntity findByEmail(String email);
    UserEntity updateAccessDates(UserEntity userEntity);
    
}
