package br.com.fabrisio.guideme.repository;

import br.com.fabrisio.guideme.entity.roadmap.StepEntity;
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

    @Query("SELECT upe FROM UserProgressEntity upe WHERE upe.user.id = :user AND upe.step.id = :step")
    UserProgressEntity findByUserAndStep(@Param("user") Long user, @Param("step") Long step);

    @Query("SELECT upe.step FROM UserProgressEntity upe WHERE upe.user.id = :user AND upe.isDone = false AND upe.isOpen = true")
    List<StepEntity> findByUserAndStepIsFinally(@Param("user") Long user);

    @Query("SELECT upe FROM UserProgressEntity upe WHERE upe.step.layer.id =:layerId AND upe.step NOT IN(:step)")
    List<UserProgressEntity> faltaStep(Long layerId, List<StepEntity> step);

}
