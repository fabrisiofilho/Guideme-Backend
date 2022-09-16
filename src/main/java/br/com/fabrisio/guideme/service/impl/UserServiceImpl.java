package br.com.fabrisio.guideme.service.impl;

import br.com.fabrisio.guideme.configuration.context.GuidemeContext;
import br.com.fabrisio.guideme.dto.user.UserDTO;
import br.com.fabrisio.guideme.entity.user.InventoryEntity;
import br.com.fabrisio.guideme.entity.user.ProfileEnum;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private FirebaseBlobStorage firebaseBlobStorage;

    @Override
    public UserEntity create(UserDTO userDto) {
        var user = UserEntity.builder()
                .name(userDto.getName())
                .username(userDto.getUsername())
                .email(userDto.getEmail())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .createDate(LocalDateTime.now())
                .lastUpdateDate(LocalDateTime.now())
                .coins(0D)
                .exps(0D)
                .points(0D)
                .profile(ProfileEnum.ALUNO)
                .inventory(new InventoryEntity())
                .build();
        return repository.save(user);
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
        return repository.findByEmail(email).orElseThrow(()-> {throw new NotFoundException("Não foi encontrado o usuario com o email");});
    }

    @Override
    public UserEntity findByUsername(String username) {
        return repository.findByUsername(username).orElseThrow(()-> {throw new NotFoundException("Não foi encontrado o usuario com o username");});
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
    public UserEntity updateUser(UserDTO.UpdateUser dto) {
        UserEntity entity = read(dto.getId());
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        if (!entity.getPassword().equals(dto.getPassword())) {
            entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
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
    public List<UserEntity> ranking() {
        return repository.ranking();
    }

    @Override
    public UserEntity updateAccessDates(UserEntity userEntity) {
        userEntity.setLastUpdateDate(LocalDateTime.now());
        return repository.save(userEntity);
    }
}
