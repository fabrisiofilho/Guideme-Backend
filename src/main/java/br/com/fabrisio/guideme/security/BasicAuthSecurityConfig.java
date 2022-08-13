package br.com.fabrisio.guideme.security;

import br.com.fabrisio.guideme.configuration.constant.ResponseValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

@Order(1)
@Configuration
@EnableWebSecurity
public class BasicAuthSecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String[] PUBLIC_MATCHERS_GET = {"/tests/**"};
    private static final String[] PUBLIC_MATCHERS_POST = {"/tests/**"};

    private final PasswordEncoder passwordEncoder;
    private final BasicEntryPointSecurity basicEntryPointSecurity;

    @Autowired
    public BasicAuthSecurityConfig(PasswordEncoder passwordEncoder, BasicEntryPointSecurity basicEntryPointSecurity) {
        this.passwordEncoder = passwordEncoder;
        this.basicEntryPointSecurity = basicEntryPointSecurity;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder.encode("admin")).roles(ResponseValues.BASIC);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.requestMatchers().antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET);
        http.requestMatchers().antMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST);

        http.authorizeRequests().antMatchers(PUBLIC_MATCHERS_GET).hasRole(ResponseValues.BASIC);
        http.authorizeRequests().antMatchers(PUBLIC_MATCHERS_POST).hasRole(ResponseValues.BASIC);
        http.httpBasic().authenticationEntryPoint(basicEntryPointSecurity).and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.cors().and().csrf().disable();
    }
}

