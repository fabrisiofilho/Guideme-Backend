package br.com.fabrisio.guideme.service.impl;

import br.com.fabrisio.guideme.dto.roadmap.RoadmapDTO;
import br.com.fabrisio.guideme.dto.roadmap.StepDTO;
import br.com.fabrisio.guideme.entity.roadmap.RoadmapEntitty;
import br.com.fabrisio.guideme.entity.roadmap.StepEntity;
import br.com.fabrisio.guideme.exception.BuninessException;
import br.com.fabrisio.guideme.repository.RoadmapRepository;
import br.com.fabrisio.guideme.repository.StepRepository;
import br.com.fabrisio.guideme.service.RoadmapService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoadmapServiceImpl implements RoadmapService {

    @Autowired
    private RoadmapRepository roadmapRepository;

    @Autowired
    private StepRepository stepRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public RoadmapEntitty create(RoadmapDTO roadmapDTO) {
        return roadmapRepository.save(modelMapper.map(roadmapDTO, RoadmapEntitty.class));
    }

    @Override
    public RoadmapEntitty read(Long id) {
        return roadmapRepository.findById(id).orElseThrow(() -> { throw new BuninessException("Não foi possivel encontrar o roadmap."); });
    }

    @Override
    public RoadmapEntitty update(RoadmapDTO roadmapDTO) {
        RoadmapEntitty roadmapEntitty = read(roadmapDTO.getId());
        return roadmapRepository.save(roadmapEntitty.atualizar(roadmapDTO));
    }

    @Override
    public RoadmapEntitty delete(Long id) {
        RoadmapEntitty roadmapEntitty = read(id);
        roadmapRepository.deleteById(id);
        return roadmapEntitty;
    }

    @Override
    @Transactional
    public RoadmapEntitty addStep(Long id, StepDTO stepDTO) {
        StepEntity stepEntity = modelMapper.map(stepDTO, StepEntity.class);
        RoadmapEntitty roadmapEntitty = read(id);
        stepEntity.setRoadmap(roadmapEntitty);
        stepRepository.save(stepEntity);
        return read(roadmapEntitty.getId());
    }

}
