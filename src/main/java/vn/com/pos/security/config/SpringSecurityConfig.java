package vn.com.pos.security.config;

import lombok.AllArgsConstructor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
@AllArgsConstructor
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled=true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    private final DataSource dataSource;

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication().dataSource(dataSource)
//                .usersByUsernameQuery("select username, password, enabled"
//                        + " from users where username = ?")
//                .authoritiesByUsernameQuery("select username, authority "
//                        + "from authorities where username = ?")
//                .passwordEncoder(new BCryptPasswordEncoder());
//    }

    //for various testing purposes, it's better to store the user
    //credentials and then authenticate in memory than to use a proper database
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("admin")
                .password("{noop}admin@password")//{noop} makes sure that the password encoder doesn't do anything
                .roles("ADMIN") // Role of the user
                .and()
                .withUser("user")
                .password("{noop}user@password")
                .credentialsExpired(false)
                .accountExpired(false)
                .accountLocked(false)
                .roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().hasAnyRole("ADMIN", "USER") //doesn't have the prefix ROLE_
                .and().httpBasic();// Use Basic authentication
    }
}
