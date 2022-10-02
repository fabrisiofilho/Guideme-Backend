package br.com.fabrisio.guideme.entity.challenger;

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
@Table(name = "challenger")
public class ChallengerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String question;

    private String result;

    @Column(name = "bounty_coin")
    private Double bountyCoin;

    @Column(name = "bounty_xp")
    private Double bountyXp;

    private Double points;

    private Boolean isDone;

}
