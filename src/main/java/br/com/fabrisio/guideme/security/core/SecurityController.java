package br.com.fabrisio.guideme.security.core;

import br.com.fabrisio.guideme.security.core.dto.ForgotPasswordDto;
import br.com.fabrisio.guideme.security.core.dto.ResetPasswordDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class SecurityController {

    @Autowired
    private SecurityService securityService;

    private static final String LOGIN = "/login";
    private static final String FORGOT_PASSWORD = "/forgotPassword";
    private static final String RESET_PASSWORD = "/resetPassword";

    /**
     * Login
     */
    @GetMapping(LOGIN)
    public String login() {
        return "pages/login";
    }

    /**
     * ForgotPassword
     */
    @GetMapping(FORGOT_PASSWORD)
    public String forgotget(){
        return "pages/forgotPassword";
    }

    @PostMapping(FORGOT_PASSWORD)
    public String forgotpost(ForgotPasswordDto dto) {
        return null;
    }


    /**
     * ResetPassword
     */
    @GetMapping(RESET_PASSWORD)
    public String resetget(){
        return "pages/resetPassword";
    }

    @PostMapping(RESET_PASSWORD)
    public String resetpost(@RequestBody ResetPasswordDto dto){
        return "sucesso";
    }

}
