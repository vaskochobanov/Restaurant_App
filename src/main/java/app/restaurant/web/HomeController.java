package app.restaurant.web;

import app.restaurant.services.MealService;
import app.restaurant.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {
    private final UserService userService;
    private final MealService mealService;

    public HomeController(UserService userService, MealService mealService) {
        this.userService = userService;
        this.mealService = mealService;
    }

    @GetMapping("/")
    public String getIndex(Model model) {
        model.addAttribute("promSalad", mealService.getPromotedDish("SALAD"));
        model.addAttribute("promMainDish", mealService.getPromotedDish("MAIN_DISH"));
        model.addAttribute("promDessert", mealService.getPromotedDish("DESSERT"));
        return "index";
    }
    @GetMapping("/home")
    public String getHome(Model model) {
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
            model.addAttribute("waiterId", userService.getIdByUsername(authentication.getName()));
            return "home-waiter";
        }
        return null;
    }
}
