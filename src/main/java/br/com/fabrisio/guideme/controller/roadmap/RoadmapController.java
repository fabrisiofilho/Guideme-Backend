package br.com.fabrisio.guideme.controller.roadmap;

import br.com.fabrisio.guideme.configuration.SuccessResponse;
import br.com.fabrisio.guideme.dto.roadmap.*;
import br.com.fabrisio.guideme.entity.roadmap.ContentEntity;
import br.com.fabrisio.guideme.entity.roadmap.LayerEntity;
import br.com.fabrisio.guideme.entity.roadmap.RoadmapEntitty;
import br.com.fabrisio.guideme.entity.roadmap.StepEntity;
import br.com.fabrisio.guideme.service.*;
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

    @Autowired
    private ContentService contentService;

    @Autowired
    private NotificationService notificationService;

    @PostMapping("")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<RoadmapDTO> register(@RequestBody RoadmapDTO roadmap){
        RoadmapEntitty entity = roadmapService.create(roadmap);
        RoadmapDTO dto = modelMapper.map(entity, RoadmapDTO.class);
        return new SuccessResponse<RoadmapDTO>().handle(dto, this.getClass(), HttpStatus.OK);
    }

    @PostMapping("/addLayer")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<RoadmapDTO> addLayer(@RequestBody LayerDTO dto){
        RoadmapEntitty entity = roadmapService.addLayer(dto.getIdRoadmap(), dto);
        RoadmapDTO roadmapDTO = modelMapper.map(entity, RoadmapDTO.class);
        return new SuccessResponse<RoadmapDTO>().handle(roadmapDTO, this.getClass(), HttpStatus.OK);
    }

    @PostMapping("/addStep")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<RoadmapDTO> addStep(@RequestBody StepDTO dto){
        RoadmapEntitty entity = roadmapService.addStepToLayer(dto.getIdLayer(), dto);
        notificationService.create("Novo modulo cadastrado.");
        RoadmapDTO roadmapDTO = modelMapper.map(entity, RoadmapDTO.class);
        return new SuccessResponse<RoadmapDTO>().handle(roadmapDTO, this.getClass(), HttpStatus.OK);
    }

    @PostMapping("/addContent")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<RoadmapDTO> addContent(@RequestBody ContentDTO dto){
        RoadmapEntitty entity = roadmapService.addContentToStep(dto.getIdStep(), dto);
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
    public ResponseEntity<RoadmapDTO> deleteStepById(@PathVariable Long id){
        StepEntity stepEntity = stepService.delete(id);
        RoadmapEntitty entity = roadmapService.read(stepEntity.getLayer().getRoadmap().getId());
        RoadmapDTO dto = modelMapper.map(entity, RoadmapDTO.class);
        return new SuccessResponse<RoadmapDTO>().handle(dto, this.getClass(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/layer/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<RoadmapDTO> deleteLayerById(@PathVariable Long id){
        LayerEntity layerEntity = layerService.delete(id);
        RoadmapEntitty entity = roadmapService.read(layerEntity.getRoadmap().getId());
        RoadmapDTO dto = modelMapper.map(entity, RoadmapDTO.class);
        return new SuccessResponse<RoadmapDTO>().handle(dto, this.getClass(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/content/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<RoadmapDTO> deleteContentById(@PathVariable Long id){
        ContentEntity contentEntity = contentService.delete(id);
        RoadmapEntitty entity = roadmapService.read(contentEntity.getStep().getLayer().getRoadmap().getId());
        RoadmapDTO dto = modelMapper.map(entity, RoadmapDTO.class);
        return new SuccessResponse<RoadmapDTO>().handle(dto, this.getClass(), HttpStatus.OK);
    }

    @GetMapping("/get")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'ALUNO')")
    public ResponseEntity<RoadmapDTO> getRoadmapByUserProgress(){
        RoadmapDTO dto = roadmapService.getRoadmapByUserProgress();
        return new SuccessResponse<RoadmapDTO>().handle(dto, this.getClass(), HttpStatus.OK);
    }

    @PostMapping("/addValidate")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<RoadmapDTO> addValidate(@RequestBody QuestionDTO dto){
        RoadmapEntitty entity = roadmapService.addValidateStep(dto);
        RoadmapDTO roadmapDTO = modelMapper.map(entity, RoadmapDTO.class);
        return new SuccessResponse<RoadmapDTO>().handle(roadmapDTO, this.getClass(), HttpStatus.OK);
    }

    @GetMapping("/question/{id}")
    @PreAuthorize("hasAnyAuthority('ALUNO')")
    public ResponseEntity<StepDTO> getQuestion(@PathVariable Long id){
        StepEntity entity = stepService.read(id);
        StepDTO stepDTO = modelMapper.map(entity, StepDTO.class);
        return new SuccessResponse<StepDTO>().handle(stepDTO, this.getClass(), HttpStatus.OK);
    }

}
