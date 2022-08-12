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
@Table(name = "layer")
public class LayerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="roadmap_id", nullable=false)
    private RoadmapEntitty roadmap;

    @OneToMany(mappedBy = "layer")
    private List<StepEntity> steps;

}
