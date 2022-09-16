package br.com.fabrisio.guideme.controller.user;

import br.com.fabrisio.guideme.dto.user.UserDTO;
import br.com.fabrisio.guideme.entity.user.UserEntity;
import br.com.fabrisio.guideme.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/user")
@PreAuthorize("isAuthenticated()")
public class UserController {

    @Autowired
    @Qualifier("userServiceImpl")
    UserService service;

    @GetMapping("list")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<List<UserEntity>> getPage(){
        return ResponseEntity.ok(service.list());
    }

    @GetMapping("pageable")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Page<UserEntity>> getPage(Pageable pageable){
        return ResponseEntity.ok(service.pageable(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getOne(@PathVariable Long id){
        return ResponseEntity.ok(service.read(id));
    }

    @PutMapping("")
    public ResponseEntity<UserEntity> update(@RequestBody UserDTO updated){
        return ResponseEntity.ok(service.update(updated));
    }

    @PostMapping("")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<UserEntity> create(@RequestBody UserDTO created){
        return ResponseEntity.ok(service.create(created));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<String> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.ok("Ok");
    }

    @PutMapping("/updatePhoto")
    public ResponseEntity<UserEntity> update(@RequestPart(value = "attachments", required = false) MultipartFile attachments) throws IOException {
        return ResponseEntity.ok(service.updatePhoto(attachments));
    }

    @PutMapping("/updateUser")
    public ResponseEntity<UserEntity> updateUser(@RequestBody UserDTO.UpdateUser updateUser){
        return ResponseEntity.ok(service.updateUser(updateUser));
    }

    @PutMapping("/updateName")
    public ResponseEntity<UserEntity> update(@RequestBody UserDTO.UpdateName updateName){
        return ResponseEntity.ok(service.updateName(updateName));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserEntity> findByEmail(@PathVariable String email){
        return ResponseEntity.ok(service.findByEmail(email));
    }

    @GetMapping("/ranking")
    public ResponseEntity<List<UserEntity>> ranking(){
        return ResponseEntity.ok(service.ranking());
    }

}
