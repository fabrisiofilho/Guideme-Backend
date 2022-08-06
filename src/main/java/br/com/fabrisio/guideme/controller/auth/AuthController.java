package br.com.fabrisio.guideme.controller.auth;

import br.com.fabrisio.guideme.configuration.SuccessResponse;
import br.com.fabrisio.guideme.dto.login.RefreshTokenDTO;
import br.com.fabrisio.guideme.dto.user.UserDTO;
import br.com.fabrisio.guideme.entity.user.UserEntity;
import br.com.fabrisio.guideme.security.CustomUserDetailsService;
import br.com.fabrisio.guideme.security.SecurityServiceImpl;
import br.com.fabrisio.guideme.security.util.TokenJWTSecurity;
import br.com.fabrisio.guideme.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenJWTSecurity tokenJWTSecurity;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private SecurityServiceImpl securityService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody UserDTO user){
        UserEntity userEntity = userService.create(user);
        UserDTO userDTO = modelMapper.map(userEntity, UserDTO.class);
        return new SuccessResponse<UserDTO>().handle(userDTO, this.getClass(), HttpStatus.OK);
    }

    @PostMapping("/forgotPassword")
    public ResponseEntity<String> forgot(@RequestBody UserDTO.Recover user) {
        if (Boolean.TRUE.equals(userService.isEmailInUse(user.getEmail()))) {
            securityService.sendEmailRevocer(user.getEmail());
            return ResponseEntity.ok("Ok");
        }
        return ResponseEntity.badRequest().body("Email não pertence a nenhuma usuario.");
    }

    @PostMapping("/validTokenRecover")
    public ResponseEntity<UserDTO> validTokenRecover(@RequestBody String token) {
        var user = userService.findByTokenRecover(token);
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        return new SuccessResponse<UserDTO>().handle(userDTO, this.getClass(), HttpStatus.OK);
    }

    @PostMapping("/resetPassword")
    public ResponseEntity<UserDTO> reset(@RequestBody UserDTO.ResetPassword resetPassword) {
        var user = userService.findByTokenRecover(resetPassword.getToken());
        user = userService.resetPassword(user);
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        return new SuccessResponse<UserDTO>().handle(userDTO, this.getClass(), HttpStatus.OK);
    }


    @PostMapping("/refreshToken")
    public ResponseEntity<RefreshTokenDTO> refreshToken(@RequestBody RefreshTokenDTO refreshToken){
        tokenJWTSecurity.tokenValid(refreshToken.getRefreshToken());
        var userLogged = userService.findByEmail(refreshToken.getEmail());
        var newToken = tokenJWTSecurity.generateToken(userLogged.getUsername());
        var newRefreshToken = tokenJWTSecurity.generateRefreshToken(userLogged.getUsername());

        RefreshTokenDTO request = RefreshTokenDTO.builder()
                .token(newToken)
                .refreshToken(newRefreshToken)
                .email(refreshToken.getEmail())
                .build();

        return new SuccessResponse<RefreshTokenDTO>().handle(request, this.getClass(), HttpStatus.OK);
    }

}
