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
    private String optionsOne;
    private String questionTwo;
    private String responseTwo;
    private String optionsTwo;
    private String questionThree;
    private String responseThree;
    private String optionsThree;
    private String questionFour;
    private String responseFour;
    private String optionsFour;
    private String questionFive;
    private String responseFive;
    private String optionsFive;
    private Long stepId;

}
