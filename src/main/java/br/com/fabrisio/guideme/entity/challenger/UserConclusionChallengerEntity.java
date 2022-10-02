package br.com.fabrisio.guideme.entity.challenger;

import br.com.fabrisio.guideme.entity.user.UserEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "user_conclusion_challenger")
public class UserConclusionChallengerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean isDone;

    @OneToOne
    private ChallengerEntity challengerEntity;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private UserEntity user;

}
