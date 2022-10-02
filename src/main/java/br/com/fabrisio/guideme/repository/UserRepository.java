package br.com.fabrisio.guideme.repository;

import br.com.fabrisio.guideme.entity.notification.NotificationEntity;
import br.com.fabrisio.guideme.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("SELECT p FROM UserEntity p WHERE UPPER(p.username) LIKE UPPER(:username)")
    Optional<UserEntity> findByUsername(String username);

    @Query("SELECT p FROM UserEntity p WHERE UPPER(p.email) LIKE UPPER(:email)")
    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByTokenRecover(String email);

    @Query("SELECT p FROM UserEntity p WHERE p.profile=0 ORDER BY p.points DESC")
    List<UserEntity> ranking();

    @Query("SELECT p FROM UserEntity p WHERE p.profile=0")
    List<UserEntity> findAllUsersAluno();

    @Query("SELECT n FROM NotificationEntity n WHERE n.user.id=:id AND n.isRead=false ORDER BY n.id ASC")
    List<NotificationEntity> notification(Long id);

    @Modifying
    @Query("UPDATE NotificationEntity n SET n.isRead = true WHERE n.id = :id")
    void readNotification(Long id);

}
