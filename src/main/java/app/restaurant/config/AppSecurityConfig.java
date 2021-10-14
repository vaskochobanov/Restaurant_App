package app.restaurant.config;

import app.restaurant.services.UserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public AppSecurityConfig(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/js/**", "/css/**", "/img/**").permitAll()
                .antMatchers("/", "/users/register", "/users/login").permitAll()
                .antMatchers("/**").authenticated().and().formLogin().loginProcessingUrl("/users/login")
                .usernameParameter("username").passwordParameter("password").defaultSuccessUrl("/home")
                .failureForwardUrl("/users/log-error").and().logout().logoutUrl("/logout").logoutSuccessUrl("/")
                .invalidateHttpSession(true).deleteCookies("JSESSIONID");
        http.csrf().disable();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }
}
