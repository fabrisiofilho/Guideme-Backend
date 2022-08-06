package br.com.fabrisio.guideme.service;

import br.com.fabrisio.guideme.dto.roadmap.StepDTO;
import br.com.fabrisio.guideme.entity.roadmap.StepEntity;

public interface StepService {

    StepEntity create(StepDTO userDto);
    StepEntity read(Long id);
    StepEntity update(StepDTO userDto);
    StepEntity delete(Long id);

}
