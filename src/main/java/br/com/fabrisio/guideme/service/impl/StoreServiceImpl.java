package br.com.fabrisio.guideme.service.impl;

import br.com.fabrisio.guideme.dto.store.ItemDTO;
import br.com.fabrisio.guideme.entity.store.ItemEntity;
import br.com.fabrisio.guideme.repository.ItemRepository;
import br.com.fabrisio.guideme.service.StoreService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ItemEntity createItem(ItemDTO itemDTO) {
        return itemRepository.save(modelMapper.map(itemDTO, ItemEntity.class));
    }

    @Override
    public ItemEntity readItem(Long id) {
        return itemRepository.findById(id).orElse(null);
    }

    @Override
    public ItemEntity updateItem(ItemDTO itemDTO) {
        return itemRepository.save(modelMapper.map(itemDTO, ItemEntity.class));
    }

    @Override
    public ItemEntity deleteItem(Long id) {
        ItemEntity itemEntity = readItem(id);
        itemRepository.deleteById(id);
        return itemEntity;
    }

    @Override
    public List<ItemEntity> listItens() {
        return itemRepository.findAll();
    }

    @Override
    public Page<ItemEntity> pageableItens(Pageable pageable) {
        return itemRepository.findAll(pageable);
    }

    @Override
    public Page<ItemEntity> seachItens(String title, Pageable pageable) {
        return itemRepository.search(title, pageable);
    }

}
