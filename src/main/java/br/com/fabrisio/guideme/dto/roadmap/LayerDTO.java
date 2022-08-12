package br.com.fabrisio.guideme.dto.roadmap;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LayerDTO {
    private Long id;
    private List<StepDTO> steps;
    private Long idRoadmap;
}
