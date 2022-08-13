package br.com.fabrisio.guideme.entity.user;

import br.com.fabrisio.guideme.dto.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "sys_user")
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    @Email(message = "E-mail inválido!")
    private String email;

    private String password;

    @Column(name = "profile")
    private ProfileEnum profile;

    @Column(name = "url_photo")
    private String urlPhoto;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "token_recover")
    private String tokenRecover;

    private Double coins;

    private Double points;

    private Double exps;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "inventory_id", referencedColumnName = "id")
    private InventoryEntity inventory;

    @OneToMany(mappedBy = "user")
    private List<UserProgressEntity> progress;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "last_update_date")
    private LocalDateTime lastUpdateDate;

    public UserEntity update(UserDTO userDto){
        this.name = userDto.getName();
        this.username = userDto.getUsername();
        this.email = userDto.getEmail();
        this.lastUpdateDate = LocalDateTime.now();
        return this;
    }

    public UserEntity(String name, String username, String email, String password, LocalDateTime createDate, LocalDateTime lastUpdateDate) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.createDate = createDate;
        this.lastUpdateDate = lastUpdateDate;
    }

}
