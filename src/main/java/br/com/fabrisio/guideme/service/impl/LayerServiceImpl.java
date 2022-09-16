package br.com.fabrisio.guideme.service.impl;

import br.com.fabrisio.guideme.dto.roadmap.LayerDTO;
import br.com.fabrisio.guideme.dto.roadmap.StepDTO;
import br.com.fabrisio.guideme.entity.roadmap.LayerEntity;
import br.com.fabrisio.guideme.entity.roadmap.StepEntity;
import br.com.fabrisio.guideme.exception.NotFoundException;
import br.com.fabrisio.guideme.repository.LayerRepository;
import br.com.fabrisio.guideme.repository.StepRepository;
import br.com.fabrisio.guideme.service.LayerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LayerServiceImpl implements LayerService {

    @Autowired
    private LayerRepository layerRepository;

    @Autowired
    private StepRepository stepRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public LayerEntity create(LayerDTO dto) {
        return layerRepository.save(modelMapper.map(dto, LayerEntity.class));
    }

    @Override
    public LayerEntity read(Long id) {
        return layerRepository.findById(id).orElseThrow(()->{throw new NotFoundException("Não foi encontrado o inventario");});
    }

    @Override
    public LayerEntity update(LayerDTO dto) {
        return layerRepository.save(modelMapper.map(dto, LayerEntity.class));
    }

    @Override
    public LayerEntity delete(Long id) {
        LayerEntity layerEntity = read(id);
        layerRepository.deleteById(id);
        return layerEntity;
    }

    @Override
    public LayerEntity addStep(Long id, StepDTO stepDTO) {
        StepEntity stepEntity = modelMapper.map(stepDTO, StepEntity.class);
        LayerEntity layer = read(id);
        stepEntity.setLayer(layer);
        stepRepository.save(stepEntity);
        return read(layer.getId());
    }
}
