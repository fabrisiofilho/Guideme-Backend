package br.com.fabrisio.guideme.dto.roadmap;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StepDTO {

    private Long id;
    private String title;
    private Double bountyCoin;
    private Double bountyXp;
    private boolean isDone;
    private boolean isOpen;
    private Double difficulty;
    private Double conclusion;
    private Integer layer;
    private Long idRoadmap;

}
