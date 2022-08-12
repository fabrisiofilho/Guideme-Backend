package br.com.fabrisio.guideme.service;

import br.com.fabrisio.guideme.dto.user.UserDTO;
import br.com.fabrisio.guideme.entity.user.UserEntity;
import org.h2.engine.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserService {

    UserEntity create(UserDTO userDto);
    UserEntity read(Long id);
    UserEntity update(UserDTO userDto);
    UserEntity delete(Long id);
    List<UserEntity> list();
    Page<UserEntity> pageable(Pageable pageable);
    UserEntity resetPassword(UserEntity userDto);
    Boolean isEmailInUse(String Email);
    UserEntity findByEmail(String email);
    UserEntity updateAccessDates(UserEntity userEntity);
    UserEntity findByUsername(String email);
    UserEntity updatePhoto(MultipartFile multipartFile) throws IOException;
    UserEntity updateName(UserDTO.UpdateName dto);
    UserEntity updateRecoverToken(String token, UserEntity userEntity);
    UserEntity findByTokenRecover(String token);
    List<UserEntity> ranking();
    
}
