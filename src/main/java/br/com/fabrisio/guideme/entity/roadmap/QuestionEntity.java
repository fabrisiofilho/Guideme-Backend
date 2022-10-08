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

    @Column(name = "question_two")
    private String questionTwo;

    @Column(name = "response_two")
    private String responseTwo;

    @Column(name = "question_three")
    private String questionThree;

    @Column(name = "response_three")
    private String responseThree;

    @Column(name = "question_four")
    private String questionFour;

    @Column(name = "response_four")
    private String responseFour;

    @Column(name = "question_five")
    private String questionFive;

    @Column(name = "response_five")
    private String responseFive;

}
