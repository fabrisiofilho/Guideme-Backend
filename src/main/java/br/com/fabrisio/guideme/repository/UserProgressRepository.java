package br.com.fabrisio.guideme.repository;

import br.com.fabrisio.guideme.entity.user.UserProgressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserProgressRepository extends JpaRepository<UserProgressEntity, Long> {

    @Query("SELECT upe FROM UserProgressEntity upe WHERE upe.user.id = :user")
    List<UserProgressEntity> findByUser(@Param("user") Long user);

}
