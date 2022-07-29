package br.com.fabrisio.guideme.security;

import br.com.fabrisio.guideme.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;



@Order(2)
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class ResourceServerConfig extends WebSecurityConfigurerAdapter {

    private static final String[] PUBLIC_MATCHERS = {"/h2-console/**"};
    private static final String[] PUBLIC_MATCHERS_GET = {};
    private static final String[] PUBLIC_MATCHERS_POST = {};

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final TokenJWTSecurity tokenJWTSecurity;
    private final UserService userService;

    @Autowired
    public ResourceServerConfig(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder, TokenJWTSecurity tokenJWTSecurity, UserService userService) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.tokenJWTSecurity = tokenJWTSecurity;
        this.userService = userService;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors().and().csrf().disable();
        http.authorizeRequests().antMatchers(PUBLIC_MATCHERS).permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST).permitAll();
        http.authorizeRequests().anyRequest().authenticated();

        http.addFilter(new AuthenticationSecurity(authenticationManager(), tokenJWTSecurity, userService));
//        http.addFilter(new AuthorizationSecurity(authenticationManager(), tokenJWTSecurity, userService));
//        http.addFilterAfter(new TenantInterceptorSecurity(companyService, tenantService), AuthorizationSecurity.class);
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

}
