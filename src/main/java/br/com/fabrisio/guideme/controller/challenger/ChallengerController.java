package br.com.fabrisio.guideme.controller.challenger;

import br.com.fabrisio.guideme.dto.challenger.ChallengerDTO;
import br.com.fabrisio.guideme.entity.challenger.ChallengerEntity;
import br.com.fabrisio.guideme.service.ChallengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/challenger")
public class ChallengerController {

    @Autowired
    private ChallengerService service;

    @GetMapping("/list")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'ALUNO')")
    public ResponseEntity<List<ChallengerEntity>> getPage(){
        return ResponseEntity.ok(service.listChallengers());
    }

    @GetMapping("/find")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'ALUNO')")
    public ResponseEntity<Page<ChallengerEntity>> search(@RequestParam(value = "search") String search, Pageable pageable){
        return ResponseEntity.ok(service.seachChallengers(search, pageable));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<ChallengerEntity> getOne(@PathVariable Long id){
        return ResponseEntity.ok(service.readChallenger(id));
    }

    @PutMapping("")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<ChallengerEntity> update(@RequestBody ChallengerDTO updated){
        return ResponseEntity.ok(service.updateChallenger(updated));
    }

    @PostMapping("")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<ChallengerEntity> create(@RequestBody ChallengerDTO created){
        return ResponseEntity.ok(service.createChallenger(created));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<String> delete(@PathVariable Long id){
        service.deleteChallenger(id);
        return ResponseEntity.ok("Ok");
    }


}
