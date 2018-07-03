package pl.sda.aborolabis.project;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.sda.aborolabis.project.handler.SuccessLoginHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final SuccessLoginHandler successLoginHandler;

    public WebSecurityConfig(SuccessLoginHandler successLoginHandler) {
        this.successLoginHandler = successLoginHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/index").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/doctor").access("hasAuthority('DOCTOR')")
                .antMatchers("/doctor/*").access("hasAuthority('DOCTOR')")
                .antMatchers("/patient").access("hasAuthority('PATIENT')")
                .antMatchers("/patient/*").access("hasAuthority('PATIENT')")
                .antMatchers("/nurse").access("hasAuthority('NURSE')")
                .antMatchers("/nurse/*").access("hasAuthority('NURSE')")
                .and()
                .formLogin()
                .loginPage("/login")
                .successHandler(successLoginHandler)
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}