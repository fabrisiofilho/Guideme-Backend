package br.com.fabrisio.guideme.entity;

import br.com.fabrisio.guideme.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sys_user")
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String username;

    private String email;

    private String password;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "last_update_date")
    private LocalDateTime lastUpdateDate;

    public UserEntity update(UserDto userDto){
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
