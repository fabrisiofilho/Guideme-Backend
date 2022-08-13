package br.com.fabrisio.guideme.controller.roadmap;

import br.com.fabrisio.guideme.dto.user.UserProgressDTO;
import br.com.fabrisio.guideme.service.UserProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/advance")
public class AdvanceController {

    @Autowired
    private UserProgressService userProgressService;

    @PostMapping("")
    @PreAuthorize("hasAnyAuthority('ALUNO')")
    public void registerProgress(@RequestBody UserProgressDTO roadmap){
        userProgressService.create(roadmap);
    }

}
