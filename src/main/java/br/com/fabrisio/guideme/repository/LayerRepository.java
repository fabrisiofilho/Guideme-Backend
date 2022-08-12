package br.com.fabrisio.guideme.repository;

import br.com.fabrisio.guideme.entity.roadmap.LayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LayerRepository extends JpaRepository<LayerEntity, Long> {
}
