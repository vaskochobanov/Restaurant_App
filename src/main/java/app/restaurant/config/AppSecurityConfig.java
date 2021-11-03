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
                .antMatchers("/tables/online-orders").hasRole("WAITER")
                .antMatchers("/tables/edit-order/**").hasRole("WAITER")
                .antMatchers("/api/close-order", "/api/waiter-home/**", "/api/waiter-menu").hasRole("WAITER")
                .antMatchers("/api/new-order", "/api/edit-order/**", "/api/waiter-online-orders").hasRole("WAITER")
                .antMatchers("/meals/meals-menu/**", "/api/close-online-order").hasRole("WAITER")
                .antMatchers("/tables/online-orders").hasRole("WAITER")
                .antMatchers("/home-customer-order", "/api/mew-order-cust", "/api/cust-order/**").hasRole("CUSTOMER")
                .antMatchers("/prep/**", "/api/drinks").hasRole("BARMAN")
                .antMatchers("/prep/**", "/api/salads").hasRole("STARTERS")
                .antMatchers("/prep/**", "/api/main-dish").hasRole("CHEF")
                .antMatchers("/prep/**", "/api/desserts").hasRole("BAKER")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/ingredients/**", "/api/ingredients-names").hasRole("ADMIN")
                .antMatchers("/meals/**").hasRole("ADMIN")
                .antMatchers("/tables/**").hasRole("ADMIN")
                .antMatchers("/api/meals", "/api/ingredients", "/api/users", "/api/tables").hasRole("ADMIN")
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
