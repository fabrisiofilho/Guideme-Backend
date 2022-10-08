package br.com.fabrisio.guideme.repository;

import br.com.fabrisio.guideme.entity.roadmap.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository  extends JpaRepository<QuestionEntity, Long> {
}
