package br.com.fabrisio.guideme.controller.roadmap;

import br.com.fabrisio.guideme.configuration.SuccessResponse;
import br.com.fabrisio.guideme.dto.roadmap.LayerDTO;
import br.com.fabrisio.guideme.dto.roadmap.RoadmapDTO;
import br.com.fabrisio.guideme.dto.roadmap.StepDTO;
import br.com.fabrisio.guideme.entity.roadmap.LayerEntity;
import br.com.fabrisio.guideme.entity.roadmap.RoadmapEntitty;
import br.com.fabrisio.guideme.entity.roadmap.StepEntity;
import br.com.fabrisio.guideme.service.LayerService;
import br.com.fabrisio.guideme.service.RoadmapService;
import br.com.fabrisio.guideme.service.StepService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roadmap")
public class RoadmapController {

    @Autowired
    private RoadmapService roadmapService;

    @Autowired
    private LayerService layerService;

    @Autowired
    private StepService stepService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<RoadmapDTO> register(@RequestBody RoadmapDTO roadmap){
        RoadmapEntitty entity = roadmapService.create(roadmap);
        RoadmapDTO dto = modelMapper.map(entity, RoadmapDTO.class);
        return new SuccessResponse<RoadmapDTO>().handle(dto, this.getClass(), HttpStatus.OK);
    }

    @PostMapping("/addLayer")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<RoadmapDTO> addStep(@RequestBody LayerDTO dto){
        RoadmapEntitty entity = roadmapService.addLayer(dto.getIdRoadmap(), dto);
        RoadmapDTO roadmapDTO = modelMapper.map(entity, RoadmapDTO.class);
        return new SuccessResponse<RoadmapDTO>().handle(roadmapDTO, this.getClass(), HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'ALUNO')")
    public ResponseEntity<RoadmapDTO> findById(@PathVariable Long id){
        RoadmapEntitty entity = roadmapService.read(id);
        RoadmapDTO dto = modelMapper.map(entity, RoadmapDTO.class);
        return new SuccessResponse<RoadmapDTO>().handle(dto, this.getClass(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public void deleteById(@PathVariable Long id){
        roadmapService.delete(id);
    }

    @DeleteMapping("/delete/step/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<RoadmapDTO> deleteLayerById(@PathVariable Long id){
        LayerEntity layerEntity = layerService.delete(id);
        RoadmapEntitty entity = roadmapService.read(layerEntity.getRoadmap().getId());
        RoadmapDTO dto = modelMapper.map(entity, RoadmapDTO.class);
        return new SuccessResponse<RoadmapDTO>().handle(dto, this.getClass(), HttpStatus.OK);
    }

}
