package app.restaurant.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {
    @GetMapping("/")
    public String getIndex() {
        return "index";
    }
    @GetMapping("/home")
    public String getHome() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<String> roles = authentication.getAuthorities().stream().map(a -> a.getAuthority()).collect(Collectors.toList());
        String role = roles.get(0);
        if (role.equals("ROLE_ADMIN")) {
            return "home-admin";
        }
        else if (role.equals("ROLE_BARMAN")) {
            return "home-barman";
        }
        return null;
    }
}
