package br.com.fabrisio.guideme.dto.roadmap;

import lombok.*;

import java.util.List;

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
    private Double difficulty;
    private Double conclusion;
    private Integer layer;
    private List<ContentDTO> contents;
    private Long idLayer;
}
