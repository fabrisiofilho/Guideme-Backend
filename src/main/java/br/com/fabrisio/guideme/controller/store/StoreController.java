package br.com.fabrisio.guideme.controller.store;

import br.com.fabrisio.guideme.dto.store.ItemDTO;
import br.com.fabrisio.guideme.dto.user.UserDTO;
import br.com.fabrisio.guideme.entity.store.ItemEntity;
import br.com.fabrisio.guideme.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/store")
public class StoreController {

    @Autowired
    private StoreService service;

    @GetMapping("/item/list")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'ALUNO')")
    public ResponseEntity<List<ItemEntity>> getPage(){
        return ResponseEntity.ok(service.listItens());
    }

    @GetMapping("/item/find")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'ALUNO')")
    public ResponseEntity<Page<ItemEntity>> search(@RequestParam(value = "search") String search,Pageable pageable){
        return ResponseEntity.ok(service.seachItens(search, pageable));
    }

    @GetMapping("/item/pageable")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'ALUNO')")
    public ResponseEntity<Page<ItemEntity>> getPage(Pageable pageable){
        return ResponseEntity.ok(service.pageableItens(pageable));
    }

    @GetMapping("/item/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'ALUNO')")
    public ResponseEntity<ItemEntity> getOne(@PathVariable Long id){
        return ResponseEntity.ok(service.readItem(id));
    }

    @PutMapping("/item")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<ItemEntity> update(@RequestBody ItemDTO updated){
        return ResponseEntity.ok(service.updateItem(updated));
    }

    @PostMapping("/item")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<ItemEntity> create(@RequestBody ItemDTO created){
        return ResponseEntity.ok(service.createItem(created));
    }

    @DeleteMapping("/item/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<String> delete(@PathVariable Long id){
        service.deleteItem(id);
        return ResponseEntity.ok("Ok");
    }

}
