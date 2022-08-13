package br.com.fabrisio.guideme.dto.challenger;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChallengerDTO {

    private Long id;
    private String title;
    private String question;
    private String result;
    private Double bountyCoin;
    private Double bountyXp;


}
