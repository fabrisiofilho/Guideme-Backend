package br.com.fabrisio.guideme.service;

import br.com.fabrisio.guideme.dto.roadmap.ContentDTO;
import br.com.fabrisio.guideme.dto.roadmap.StepDTO;
import br.com.fabrisio.guideme.entity.roadmap.StepEntity;

public interface StepService {

    StepEntity create(StepDTO dto);
    StepEntity read(Long id);
    StepEntity update(StepDTO dto);
    StepEntity delete(Long id);
    StepEntity addContent(Long id, ContentDTO contentDTO);
}
