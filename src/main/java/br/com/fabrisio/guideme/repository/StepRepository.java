package br.com.fabrisio.guideme.repository;

import br.com.fabrisio.guideme.entity.roadmap.StepEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StepRepository extends JpaRepository<StepEntity, Long> {
}
