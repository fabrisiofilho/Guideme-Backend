package br.com.fabrisio.guideme.entity.roadmap;

import br.com.fabrisio.guideme.dto.roadmap.RoadmapDTO;
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
@Table(name = "roadmap")
public class RoadmapEntitty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @OneToMany(mappedBy = "roadmap")
    private List<StepEntity> step;

    public RoadmapEntitty atualizar(RoadmapDTO roadmapDTO) {
        this.title = roadmapDTO.getTitle();
        return this;
    }

}
