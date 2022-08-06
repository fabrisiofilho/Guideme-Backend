package br.com.fabrisio.guideme.repository;

import br.com.fabrisio.guideme.entity.roadmap.RoadmapEntitty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoadmapRepository extends JpaRepository<RoadmapEntitty, Long> {
}
