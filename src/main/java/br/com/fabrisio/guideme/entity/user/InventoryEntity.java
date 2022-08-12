package br.com.fabrisio.guideme.entity.user;

import br.com.fabrisio.guideme.entity.store.ItemEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "inventory")
public class InventoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "inventory")
    private UserEntity user;

    @OneToMany
    private List<ItemEntity> items;

}
