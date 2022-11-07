package br.com.fabrisio.guideme.entity.roadmap;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "question")
public class QuestionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "question_one")
    private String questionOne;

    @Column(name = "response_one")
    private String responseOne;

    @Column(name = "options_one")
    private String optionsOne;

    @Column(name = "question_two")
    private String questionTwo;

    @Column(name = "response_two")
    private String responseTwo;

    @Column(name = "options_two")
    private String optionsTwo;

    @Column(name = "question_three")
    private String questionThree;

    @Column(name = "response_three")
    private String responseThree;

    @Column(name = "options_three")
    private String optionsThree;

    @Column(name = "question_four")
    private String questionFour;

    @Column(name = "response_four")
    private String responseFour;

    @Column(name = "options_four")
    private String optionsFour;

    @Column(name = "question_five")
    private String questionFive;

    @Column(name = "response_five")
    private String responseFive;

    @Column(name = "options_five")
    private String optionsFive;

}
