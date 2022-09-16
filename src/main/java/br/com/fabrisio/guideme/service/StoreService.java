package br.com.fabrisio.guideme.service;

import br.com.fabrisio.guideme.dto.store.ItemDTO;
import br.com.fabrisio.guideme.entity.store.ItemEntity;
import br.com.fabrisio.guideme.entity.user.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StoreService {

    ItemEntity createItem(ItemDTO dto);
    ItemEntity readItem(Long id);
    ItemEntity updateItem(ItemDTO dto);
    ItemEntity deleteItem(Long id);
    List<ItemEntity> listItens();
    Page<ItemEntity> pageableItens(Pageable pageable);
    Page<ItemEntity> seachItens(String title, Pageable pageable);
    ItemEntity buyItem(UserEntity user, Long idItem);

}
