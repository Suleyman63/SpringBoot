package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfigration extends WebSecurityConfigurerAdapter {

    private PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfigration(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.
                csrf().disable().
                authorizeRequests().
                antMatchers("/", "index", "/css/*", "/js/*").permitAll().
                anyRequest().
                authenticated().
                and().
                httpBasic().
                and().
                formLogin().
                loginPage("/login").permitAll().
                defaultSuccessUrl("/success").and().
                logout().
                clearAuthentication(true).
                invalidateHttpSession(true).
                deleteCookies("JESSIONID").
                logoutSuccessUrl("/login");
    }


    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails user1 = User.builder().username("user").
                password(passwordEncoder.encode("1234")).
                //roles("USER").build();
                authorities(KisiRole.USER.otoriteleriAl()).build();

        UserDetails admin1 = User.builder().username("admin").
                password(passwordEncoder.encode("5678")).
                //roles("ADMIN").build();
                authorities(KisiRole.ADMIN.otoriteleriAl()).build();

        return new InMemoryUserDetailsManager(user1,admin1);
    }
}
