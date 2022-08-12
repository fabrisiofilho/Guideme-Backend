package br.com.fabrisio.guideme.entity.user;

import br.com.fabrisio.guideme.entity.roadmap.StepEntity;
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
@Table(name = "user_progress")
public class UserProgressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean isDone;

    private boolean isOpen;

    @OneToOne
    private StepEntity step;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private UserEntity user;

}
