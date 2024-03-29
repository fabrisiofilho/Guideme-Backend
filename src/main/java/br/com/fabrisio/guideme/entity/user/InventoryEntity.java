package br.com.fabrisio.guideme.entity.user;

import br.com.fabrisio.guideme.entity.store.ItemEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
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

    @JsonBackReference
    @OneToOne(mappedBy = "inventory")
    private UserEntity user;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
            name = "inventory_items",
            joinColumns = @JoinColumn(name = "inventory_id"),
            inverseJoinColumns = @JoinColumn(name = "items_id"))
    private List<ItemEntity> items;

}
