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
@Table(name = "content")
public class ContentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String payload;

    private String code;

    @Column(name = "url_video")
    private String urlVideo;

    @Column(name = "link_one")
    private String linkOne;

    @Column(name = "link_two")
    private String linkTwo;

    @Column(name = "link_tree")
    private String linkTree;

    @ManyToOne
    @JoinColumn(name="step_id", nullable=false)
    private StepEntity step;

}
