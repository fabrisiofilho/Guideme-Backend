package br.com.fabrisio.guideme.dto.user;

import br.com.fabrisio.guideme.entity.store.ItemEntity;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InventoryDTO {
    private Long id;
    private List<ItemEntity> items;
}
