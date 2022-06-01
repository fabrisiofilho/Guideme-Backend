package br.com.fabrisio.guideme.service.impl;

import br.com.fabrisio.guideme.dto.UserDto;
import br.com.fabrisio.guideme.entity.UserEntity;
import br.com.fabrisio.guideme.exception.NotFoundException;
import br.com.fabrisio.guideme.repository.UserRepository;
import br.com.fabrisio.guideme.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserEntity create(UserDto userDto) {
        return repository.save(new UserEntity(userDto.getName(), userDto.getUsername(), userDto.getEmail(), passwordEncoder.encode(userDto.getPassword()), LocalDateTime.now(), LocalDateTime.now()));
    }

    @Override
    public UserEntity read(Long id) {
        return repository.findById(id).orElseThrow(()-> {throw  new NotFoundException("Not Found");});
    }

    @Override
    public UserEntity update(UserDto userDto) {
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
    public UserEntity resetPassword(UserDto userDto) {
        UserEntity userEntity = read(userDto.getId());
        userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return repository.save(userEntity);
    }
}
