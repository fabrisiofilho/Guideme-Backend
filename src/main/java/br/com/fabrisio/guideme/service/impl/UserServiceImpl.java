package br.com.fabrisio.guideme.service.impl;

import br.com.fabrisio.guideme.configuration.context.GuidemeContext;
import br.com.fabrisio.guideme.dto.user.UserDTO;
import br.com.fabrisio.guideme.entity.user.UserEntity;
import br.com.fabrisio.guideme.exception.NotFoundException;
import br.com.fabrisio.guideme.repository.UserRepository;
import br.com.fabrisio.guideme.service.UserService;
import br.com.fabrisio.guideme.util.firebase.FirebaseBlobStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private FirebaseBlobStorage firebaseBlobStorage;

    @Override
    public UserEntity create(UserDTO userDto) {
        return repository.save(new UserEntity(userDto.getName(), userDto.getUsername(), userDto.getEmail(), passwordEncoder.encode(userDto.getPassword()), LocalDateTime.now(), LocalDateTime.now()));
    }

    @Override
    public UserEntity read(Long id) {
        return repository.findById(id).orElseThrow(()-> {throw  new NotFoundException("Not Found");});
    }

    @Override
    public UserEntity update(UserDTO userDto) {
        UserEntity userEntity = read(userDto.getId()).update(userDto);
        return repository.save(userEntity);
    }

    @Override
    public UserEntity delete(Long id) {
        UserEntity userEntity = read(id);
        repository.deleteById(id);
        return userEntity;
    }

    @Override
    public List<UserEntity> list() {
        return repository.findAll();
    }

    @Override
    public Page<UserEntity> pageable(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public UserEntity resetPassword(UserEntity userDto) {
        UserEntity userEntity = read(userDto.getId());
        userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return repository.save(userEntity);
    }

    @Override
    public Boolean isEmailInUse(String email) {
        return !Objects.isNull(repository.findByEmail(email));
    }

    @Override
    public UserEntity findByEmail(String email) {
        return repository.findByEmail(email).orElse(null);
    }

    @Override
    public UserEntity findByUsername(String username) {
        return repository.findByUsername(username).orElse(null);
    }

    @Override
    public UserEntity updatePhoto(MultipartFile multipartFile) throws IOException {
        UserEntity entity = GuidemeContext.getCurrentUser();

        if (Objects.nonNull(entity.getFileName())) {
            firebaseBlobStorage.deleteFile(entity.getFileName());
        }

        if (Objects.nonNull(multipartFile)) {
            String fileName = firebaseBlobStorage.getFileNamePhotoProfile(multipartFile, entity);
            String url = firebaseBlobStorage.upload(multipartFile, fileName);
            entity.setUrlPhoto(url);
            entity.setFileName(fileName);
            return repository.save(entity);
        }

        entity.setUrlPhoto(null);
        entity.setFileName(null);
        return repository.save(entity);
    }

    @Override
    public UserEntity updateName(UserDTO.UpdateName dto) {
        UserEntity entity = read(dto.getId());
        entity.setName(dto.getName());
        return repository.save(entity);
    }

    @Override
    public UserEntity updateRecoverToken(String token, UserEntity userEntity) {
        userEntity.setTokenRecover(token);
        return repository.save(userEntity);
    }

    @Override
    public UserEntity findByTokenRecover(String token) {
        return repository.findByTokenRecover(token).orElseThrow(()-> {throw new NotFoundException("Não foi encontrado o usuario com o token");});
    }

    @Override
    public UserEntity updateAccessDates(UserEntity userEntity) {
        userEntity.setLastUpdateDate(LocalDateTime.now());
        return repository.save(userEntity);
    }
}
