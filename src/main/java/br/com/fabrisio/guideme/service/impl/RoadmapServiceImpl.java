package br.com.fabrisio.guideme.service.impl;

import br.com.fabrisio.guideme.configuration.context.GuidemeContext;
import br.com.fabrisio.guideme.dto.roadmap.ContentDTO;
import br.com.fabrisio.guideme.dto.roadmap.LayerDTO;
import br.com.fabrisio.guideme.dto.roadmap.RoadmapDTO;
import br.com.fabrisio.guideme.dto.roadmap.StepDTO;
import br.com.fabrisio.guideme.entity.roadmap.ContentEntity;
import br.com.fabrisio.guideme.entity.roadmap.LayerEntity;
import br.com.fabrisio.guideme.entity.roadmap.RoadmapEntitty;
import br.com.fabrisio.guideme.entity.roadmap.StepEntity;
import br.com.fabrisio.guideme.entity.user.UserEntity;
import br.com.fabrisio.guideme.entity.user.UserProgressEntity;
import br.com.fabrisio.guideme.exception.BuninessException;
import br.com.fabrisio.guideme.repository.ContentRepository;
import br.com.fabrisio.guideme.repository.LayerRepository;
import br.com.fabrisio.guideme.repository.RoadmapRepository;
import br.com.fabrisio.guideme.repository.StepRepository;
import br.com.fabrisio.guideme.service.RoadmapService;
import br.com.fabrisio.guideme.service.UserProgressService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class RoadmapServiceImpl implements RoadmapService {

    @Autowired
    private RoadmapRepository roadmapRepository;

    @Autowired
    private LayerRepository layerRepository;

    @Autowired
    private StepRepository stepRepository;

    @Autowired
    private ContentRepository contentRepository;

    @Autowired
    private UserProgressService userProgressService;

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
    public RoadmapEntitty addLayer(Long id, LayerDTO layerDTO) {
        LayerEntity layerEntity = modelMapper.map(layerDTO, LayerEntity.class);
        RoadmapEntitty roadmapEntitty = read(id);
        layerEntity.setRoadmap(roadmapEntitty);
        layerRepository.save(layerEntity);
        return read(roadmapEntitty.getId());
    }

    @Override
    public RoadmapEntitty addStepToLayer(Long id, StepDTO stepDTO) {
        LayerEntity layerEntity = layerRepository.findById(id).orElseThrow(() -> { throw new BuninessException("Não foi possivel encontrar o layer.");});
        StepEntity step = modelMapper.map(stepDTO, StepEntity.class);
        step.setLayer(layerEntity);
        stepRepository.save(step);
        return read(layerEntity.getRoadmap().getId());
    }

    @Override
    public RoadmapEntitty addContentToStep(Long id, ContentDTO contentDTO) {
        StepEntity step = stepRepository.findById(id).orElseThrow(() -> { throw new BuninessException("Não foi possivel encontrar o layer.");});
        ContentEntity content = modelMapper.map(contentDTO, ContentEntity.class);
        step.getContents().add(content);
        contentRepository.save(content);
        return read(step.getLayer().getRoadmap().getId());
    }

    @Override
    public RoadmapDTO getRoadmapByUserProgress() {
        UserEntity userEntity = GuidemeContext.getCurrentUser();
        List<UserProgressEntity> userProgressEntity = userProgressService.findByUser(userEntity);
        RoadmapEntitty roadmapEntitty = read(1L);

        RoadmapDTO dto = modelMapper.map(roadmapEntitty, RoadmapDTO.class);
        dto.setLayers(new ArrayList<>());
        roadmapEntitty.getLayers().forEach(layer -> {
            LayerDTO layerDTO = modelMapper.map(layer, LayerDTO.class);
            layerDTO.setSteps(new ArrayList<>());
            layer.getSteps().forEach(step -> {
                StepDTO stepDTO = modelMapper.map(step, StepDTO.class);
                var upe = userProgressEntity.stream()
                        .filter(userProgress -> userProgress.getStep().getId().equals(step.getId()))
                        .findAny().orElse(null);
                if (Objects.nonNull(upe)) {
                    stepDTO.setIsDone(upe.isDone());
                    stepDTO.setIsOpen(upe.isOpen());
                }
                layerDTO.getSteps().add(stepDTO);
            });
            dto.getLayers().add(layerDTO);
        });

        return dto;
    }


}
