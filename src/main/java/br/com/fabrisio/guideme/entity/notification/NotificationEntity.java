package br.com.fabrisio.guideme.entity.notification;

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
@Table(name = "notification")
public class NotificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private UserEntity user;

    private String content;

    @Column(name = "is_read")
    private Boolean isRead;

}
