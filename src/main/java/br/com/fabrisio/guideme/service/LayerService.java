package br.com.fabrisio.guideme.service;

import br.com.fabrisio.guideme.dto.roadmap.LayerDTO;
import br.com.fabrisio.guideme.dto.roadmap.StepDTO;
import br.com.fabrisio.guideme.entity.roadmap.LayerEntity;

public interface LayerService {

    LayerEntity create(LayerDTO dto);
    LayerEntity read(Long id);
    LayerEntity update(LayerDTO dto);
    LayerEntity delete(Long id);
    LayerEntity addStep(Long id, StepDTO stepDTO);

}
