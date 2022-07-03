package br.com.fabrisio.guideme.security.core;

import br.com.fabrisio.guideme.dto.UserDto;
import br.com.fabrisio.guideme.security.core.dto.RegisterDto;
import br.com.fabrisio.guideme.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class RegisterController {

    private static final String REGISTER = "/register";

    @Autowired
    private UserService userService;


    /**
     * Register
     */
    @PostMapping(REGISTER)
    public String register(@Valid @RequestBody RegisterDto dto) {
        userService.create(new UserDto(dto));
        return "Registrado com sucesso";
    }

}
