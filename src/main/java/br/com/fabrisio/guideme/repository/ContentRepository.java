package br.com.fabrisio.guideme.repository;

import br.com.fabrisio.guideme.entity.roadmap.ContentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentRepository extends JpaRepository<ContentEntity, Long> {
}
