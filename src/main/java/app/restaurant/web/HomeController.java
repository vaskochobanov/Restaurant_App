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
            //todo admin must know which products are getting low
            return "home-admin";
        }
        else if (role.equals("ROLE_BARMAN")) {
            return "home-barman";
        }
        else if (role.equals("ROLE_STARTERS")) {
            return "home-starters";
        }
        else if (role.equals("ROLE_CHEF")) {
            return "home-chef";
        }
        else if (role.equals("ROLE_BAKER")) {
            return "home-baker";
        }
        else if (role.equals("ROLE_WAITER")) {
            return "home-waiter";
        }
        return null;
    }
}
