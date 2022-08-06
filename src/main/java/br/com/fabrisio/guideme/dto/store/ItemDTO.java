package br.com.fabrisio.guideme.dto.store;

import br.com.fabrisio.guideme.entity.store.CategoryItemEnum;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {

    private Long id;
    private String title;
    private CategoryItemEnum category;
    private Integer value;
    private Double price;

}
