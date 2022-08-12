package br.com.fabrisio.guideme.service.impl;

import br.com.fabrisio.guideme.dto.user.InventoryDTO;
import br.com.fabrisio.guideme.entity.user.InventoryEntity;
import br.com.fabrisio.guideme.exception.NotFoundException;
import br.com.fabrisio.guideme.repository.InventoryRepository;
import br.com.fabrisio.guideme.service.InventoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public InventoryEntity create(InventoryDTO dto) {
        return inventoryRepository.save(modelMapper.map(dto, InventoryEntity.class));
    }

    @Override
    public InventoryEntity read(Long id) {
        return inventoryRepository.findById(id).orElseThrow(()->{throw new NotFoundException("Não foi encontrado o inventario");});
    }

    @Override
    public InventoryEntity update(InventoryDTO dto) {
        return inventoryRepository.save(modelMapper.map(dto, InventoryEntity.class));
    }

    @Override
    public InventoryEntity delete(Long id) {
        InventoryEntity inventory = read(id);
        inventoryRepository.deleteById(id);
        return inventory;
    }
}
