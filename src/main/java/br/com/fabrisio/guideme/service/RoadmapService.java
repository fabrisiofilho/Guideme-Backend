package br.com.fabrisio.guideme.service;

import br.com.fabrisio.guideme.dto.roadmap.RoadmapDTO;
import br.com.fabrisio.guideme.dto.roadmap.StepDTO;
import br.com.fabrisio.guideme.entity.roadmap.RoadmapEntitty;

public interface RoadmapService {

    RoadmapEntitty create(RoadmapDTO userDto);
    RoadmapEntitty read(Long id);
    RoadmapEntitty update(RoadmapDTO userDto);
    RoadmapEntitty delete(Long id);
    RoadmapEntitty addStep(Long id, StepDTO stepDTO);

}
