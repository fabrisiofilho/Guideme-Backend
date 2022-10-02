package br.com.fabrisio.guideme.service.impl;

import br.com.fabrisio.guideme.configuration.context.GuidemeContext;
import br.com.fabrisio.guideme.dto.store.ItemDTO;
import br.com.fabrisio.guideme.entity.store.ItemEntity;
import br.com.fabrisio.guideme.entity.user.UserEntity;
import br.com.fabrisio.guideme.exception.BuninessException;
import br.com.fabrisio.guideme.repository.InventoryRepository;
import br.com.fabrisio.guideme.repository.ItemRepository;
import br.com.fabrisio.guideme.repository.UserRepository;
import br.com.fabrisio.guideme.service.NotificationService;
import br.com.fabrisio.guideme.service.StoreService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StoreServiceImpl implements StoreService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NotificationService notificationService;

    @Override
    public ItemEntity createItem(ItemDTO itemDTO) {
        notificationService.create("Novo item cadastrado.");
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
        var listItem = GuidemeContext.getCurrentUser().getInventory().getItems();
        if (listItem.isEmpty()) {
            return itemRepository.search(title, pageable);
        }
        return itemRepository.search(title, pageable, listItem);
    }

    @Override
    public ItemEntity buyItem(UserEntity user, Long idItem) {

        var item = readItem(idItem);
        user.atualizarCoin(item.getPrice());
        userRepository.save(user);

        if (user.getInventory().getItems().stream().anyMatch(it -> it.getId().equals(item.getId()))) {
            throw new BuninessException("Você já possui este item em seu inventário.");
        }

        user.getInventory().getItems().add(item);
        user.getInventory().setUser(user);
        inventoryRepository.save(user.getInventory());

        return item;
    }

}
