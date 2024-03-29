package br.com.fabrisio.guideme.repository;

import br.com.fabrisio.guideme.entity.roadmap.RoadmapEntitty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoadmapRepository extends JpaRepository<RoadmapEntitty, Long> {
}
