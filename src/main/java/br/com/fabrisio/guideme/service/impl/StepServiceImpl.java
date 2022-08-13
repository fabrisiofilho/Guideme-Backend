package br.com.fabrisio.guideme.service.impl;

import br.com.fabrisio.guideme.dto.roadmap.ContentDTO;
import br.com.fabrisio.guideme.dto.roadmap.StepDTO;
import br.com.fabrisio.guideme.entity.roadmap.ContentEntity;
import br.com.fabrisio.guideme.entity.roadmap.StepEntity;
import br.com.fabrisio.guideme.repository.ContentRepository;
import br.com.fabrisio.guideme.repository.StepRepository;
import br.com.fabrisio.guideme.service.StepService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StepServiceImpl implements StepService {

    @Autowired
    private StepRepository stepRepository;

    @Autowired
    private ContentRepository contentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public StepEntity create(StepDTO userDto) {
        return stepRepository.save(modelMapper.map(userDto, StepEntity.class));
    }

    @Override
    public StepEntity read(Long id) {
        return stepRepository.findById(id).orElse(null);
    }

    @Override
    public StepEntity update(StepDTO userDto) {
        return stepRepository.save(modelMapper.map(userDto, StepEntity.class));
    }

    @Override
    public StepEntity delete(Long id) {
        StepEntity stepEntity = read(id);
        stepRepository.deleteById(id);
        return stepEntity;
    }

    @Override
    public StepEntity addContent(Long id, ContentDTO contentDTO) {
        ContentEntity contentEntity = modelMapper.map(contentDTO, ContentEntity.class);
        StepEntity step = read(id);
        contentEntity.setStep(step);
        contentRepository.save(contentEntity);
        return read(step.getId());
    }

}
