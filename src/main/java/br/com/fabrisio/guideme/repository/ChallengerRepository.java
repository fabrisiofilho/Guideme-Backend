package br.com.fabrisio.guideme.repository;

import br.com.fabrisio.guideme.entity.challenger.ChallengerEntity;
import br.com.fabrisio.guideme.entity.store.ItemEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChallengerRepository extends JpaRepository<ChallengerEntity, Long>  {

    @Query("SELECT p FROM ChallengerEntity p WHERE UPPER(p.title) LIKE CONCAT('%',UPPER(:title) ,'%')")
    Page<ChallengerEntity> search(String title, Pageable pageable);

    @Query("SELECT p FROM ChallengerEntity p WHERE UPPER(p.title) LIKE CONCAT('%',UPPER(:title) ,'%') AND p NOT IN(:itens)")
    Page<ChallengerEntity> search(String title, Pageable pageable, List<ChallengerEntity> itens);

}
