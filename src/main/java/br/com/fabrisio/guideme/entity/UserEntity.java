package br.com.fabrisio.guideme.entity;

import br.com.fabrisio.guideme.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.persistence.*;
import javax.validation.constraints.Email;
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

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    @Email(message = "E-mail inválido!")
    private String email;

    private String password;

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

    public Boolean isAuthenticate(final String password) {
        if(this.password==null) {
            return false;
        }
        return BCrypt.checkpw(password, this.password);
    }

    public void digestPassword(String password) {
        final String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        this.password = hashedPassword;
    }

}
