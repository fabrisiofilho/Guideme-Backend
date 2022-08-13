package br.com.fabrisio.guideme.service;

import br.com.fabrisio.guideme.dto.roadmap.ContentDTO;
import br.com.fabrisio.guideme.dto.roadmap.LayerDTO;
import br.com.fabrisio.guideme.dto.roadmap.RoadmapDTO;
import br.com.fabrisio.guideme.dto.roadmap.StepDTO;
import br.com.fabrisio.guideme.entity.roadmap.RoadmapEntitty;

public interface RoadmapService {

    RoadmapEntitty create(RoadmapDTO dto);
    RoadmapEntitty read(Long id);
    RoadmapEntitty update(RoadmapDTO dto);
    RoadmapEntitty delete(Long id);
    RoadmapEntitty addLayer(Long id, LayerDTO layerDTO);
    RoadmapEntitty addStepToLayer(Long id, StepDTO stepDTO);
    RoadmapEntitty addContentToStep(Long id, ContentDTO contentDTO);
    RoadmapDTO getRoadmapByUserProgress();

}
