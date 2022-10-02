package br.com.fabrisio.guideme.repository;

import br.com.fabrisio.guideme.entity.challenger.ChallengerEntity;
import br.com.fabrisio.guideme.entity.challenger.UserConclusionChallengerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserConclusionChallengerRepository extends JpaRepository<UserConclusionChallengerEntity, Long> {

    @Query("SELECT c FROM UserConclusionChallengerEntity c WHERE c.user.id = :idUser")
    List<UserConclusionChallengerEntity> findByUser(Long idUser);

    @Query("SELECT c.challengerEntity FROM UserConclusionChallengerEntity c WHERE c.user.id = :idUser")
    List<ChallengerEntity> findByUserChallenger(Long idUser);

}
