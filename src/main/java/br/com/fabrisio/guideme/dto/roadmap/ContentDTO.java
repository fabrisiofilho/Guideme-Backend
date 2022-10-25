package br.com.fabrisio.guideme.dto.roadmap;

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
    private String code;
    private String urlVideo;
    private String linkOne;
    private String linkTwo;
    private String linkTree;
    private Long idStep;
}
