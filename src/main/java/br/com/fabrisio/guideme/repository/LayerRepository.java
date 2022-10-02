package br.com.fabrisio.guideme.repository;

import br.com.fabrisio.guideme.entity.roadmap.LayerEntity;
import br.com.fabrisio.guideme.entity.roadmap.StepEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LayerRepository extends JpaRepository<LayerEntity, Long> {

    @Query("SELECT l.steps FROM LayerEntity l WHERE l.id = 1")
    List<StepEntity> findByFirstLayer();

}
