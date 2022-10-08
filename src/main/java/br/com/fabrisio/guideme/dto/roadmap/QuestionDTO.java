package br.com.fabrisio.guideme.dto.roadmap;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDTO {

    private Long id;
    private String questionOne;
    private String responseOne;
    private String questionTwo;
    private String responseTwo;
    private String questionThree;
    private String responseThree;
    private String questionFour;
    private String responseFour;
    private String questionFive;
    private String responseFive;
    private Long stepId;

}
