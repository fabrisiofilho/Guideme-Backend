package br.com.fabrisio.guideme.security;

import br.com.fabrisio.guideme.exception.AuthenticationException;
import br.com.fabrisio.guideme.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;

@Configuration
public class LoginListener implements ApplicationListener<AuthenticationSuccessEvent> {
    private final UserService userService;

    @Autowired
    public LoginListener(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        try {
            var user = userService.findByEmail(event.getAuthentication().getName());
            userService.updateAccessDates(user);
        } catch (Exception e) {
            throw new AuthenticationException(e.getMessage(), e);
        }
    }
}

