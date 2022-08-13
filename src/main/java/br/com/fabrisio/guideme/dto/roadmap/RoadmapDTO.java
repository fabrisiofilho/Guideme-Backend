package br.com.fabrisio.guideme.dto.roadmap;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoadmapDTO {
    private Long id;
    private String title;
    private List<LayerDTO> layers;
}
