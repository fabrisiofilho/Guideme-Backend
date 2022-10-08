package br.com.fabrisio.guideme.entity.roadmap;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "step")
public class StepEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(name = "bounty_coin")
    private Double bountyCoin;

    @Column(name = "bounty_xp")
    private Double bountyXp;

    private Double difficulty;

    @ManyToOne
    @JoinColumn(name="layer_id", nullable=false)
    private LayerEntity layer;

    @OneToMany(mappedBy = "step", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ContentEntity> contents;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "question_id", referencedColumnName = "id")
    private QuestionEntity questions;

}
