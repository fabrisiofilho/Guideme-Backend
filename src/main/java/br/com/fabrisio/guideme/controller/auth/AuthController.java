package br.com.fabrisio.guideme.controller.auth;

import br.com.fabrisio.guideme.configuration.SuccessResponse;
import br.com.fabrisio.guideme.dto.RefreshTokenDTO;
import br.com.fabrisio.guideme.dto.UserDTO;
import br.com.fabrisio.guideme.entity.UserEntity;
import br.com.fabrisio.guideme.security.SecurityUserDetailsService;
import br.com.fabrisio.guideme.security.util.TokenJWTSecurity;
import br.com.fabrisio.guideme.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenJWTSecurity tokenJWTSecurity;

    @Autowired
    private SecurityUserDetailsService securityUserDetailsService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody UserDTO user){
        UserEntity userEntity = userService.create(user);
        UserDTO userDTO = modelMapper.map(userEntity, UserDTO.class);
        return new SuccessResponse<UserDTO>().handle(userDTO, this.getClass(), HttpStatus.OK);
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<RefreshTokenDTO> refreshToken(@RequestBody RefreshTokenDTO refreshToken){
        tokenJWTSecurity.tokenValid(refreshToken.getRefreshToken());

        var userLogged = securityUserDetailsService.userLogged();

        var newToken = tokenJWTSecurity.generateToken(userLogged.getUsername());
        var newRefreshToken = tokenJWTSecurity.generateRefreshToken(userLogged.getUsername());

        RefreshTokenDTO request = RefreshTokenDTO.builder()
                .token(newToken)
                .refreshToken(newRefreshToken)
                .build();

        return new SuccessResponse<RefreshTokenDTO>().handle(request, this.getClass(), HttpStatus.OK);
    }

}
