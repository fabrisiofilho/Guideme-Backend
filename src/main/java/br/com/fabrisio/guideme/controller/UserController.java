package br.com.fabrisio.guideme.controller;

import br.com.fabrisio.guideme.dto.UserDto;
import br.com.fabrisio.guideme.entity.UserEntity;
import br.com.fabrisio.guideme.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@PreAuthorize("isAuthenticated()")
public class UserController {

    @Autowired
    UserService service;

    @GetMapping("list")
    public ResponseEntity<List<UserEntity>> getPage(){
        return ResponseEntity.ok(service.list());
    }

    @GetMapping("pageable")
    public ResponseEntity<Page<UserEntity>> getPage(Pageable pageable){
        return ResponseEntity.ok(service.pageable(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getOne(@PathVariable Long id){
        return ResponseEntity.ok(service.read(id));
    }

    @PutMapping("")
    public ResponseEntity<UserEntity> update(@RequestBody UserDto updated){
        return ResponseEntity.ok(service.update(updated));
    }

    @PostMapping("")
    public ResponseEntity<UserEntity> create(@RequestBody UserDto created){
        return ResponseEntity.ok(service.create(created));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.ok("Ok");
    }

}
