package br.com.fabrisio.guideme.service;

import br.com.fabrisio.guideme.dto.user.InventoryDTO;
import br.com.fabrisio.guideme.entity.user.InventoryEntity;

public interface InventoryService {

    InventoryEntity create(InventoryDTO dto);
    InventoryEntity read(Long id);
    InventoryEntity update(InventoryDTO dto);
    InventoryEntity delete(Long id);

}
