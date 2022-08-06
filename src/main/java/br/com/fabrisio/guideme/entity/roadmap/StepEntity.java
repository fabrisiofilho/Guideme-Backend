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

    @Column(name = "is_done")
    private boolean isDone;

    @Column(name = "is_open")
    private boolean isOpen;

    private Double difficulty;

    private Double conclusion;

    private Integer layer;

    @ManyToOne
    @JoinColumn(name="roadmap_id", nullable=false)
    private RoadmapEntitty roadmap;

}
