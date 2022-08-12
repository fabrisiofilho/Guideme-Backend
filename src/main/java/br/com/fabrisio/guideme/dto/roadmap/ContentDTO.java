package br.com.fabrisio.guideme.dto.roadmap;

import br.com.fabrisio.guideme.entity.roadmap.StepEntity;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContentDTO {
    private Long id;
    private String title;
    private String payload;
    private String urlVideo;
    private String linkOne;
    private String linkTwo;
    private String linkTree;
    private StepEntity step;
}
