package app.restaurant.web;

import app.restaurant.services.*;
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
    private final OrderTypeService orderTypeService;
    private final OrderService orderService;
    private final NeedToBuyService needToBuyService;

    public HomeController(UserService userService, MealService mealService, OrderTypeService orderTypeService,
                          OrderService orderService, NeedToBuyService needToBuyService) {
        this.userService = userService;
        this.mealService = mealService;
        this.orderTypeService = orderTypeService;
        this.orderService = orderService;
        this.needToBuyService = needToBuyService;
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
            model.addAttribute("needToBuy", String.join(", ", needToBuyService.getAllUniqueIngredientsToBuy()));
            return "home-admin";
        } else if (role.equals("ROLE_BARMAN")) {
            return "home-barman";
        } else if (role.equals("ROLE_STARTERS")) {
            return "home-starters";
        } else if (role.equals("ROLE_CHEF")) {
            return "home-chef";
        } else if (role.equals("ROLE_BAKER")) {
            return "home-baker";
        } else if (role.equals("ROLE_WAITER")) {
            model.addAttribute("waiterId", userService.getIdByUsername(authentication.getName()));
            model.addAttribute("onlineAssigned",
                    orderTypeService.checkWaiterForOnline(userService.getIdByUsername(authentication.getName())));
            return "home-waiter";
        } else if (role.equals("ROLE_CUSTOMER")) {
            Long orderId = orderService.getOrderIdByCustomerId(userService.getIdByUsername(authentication.getName()));
            if (orderId == null) {
                model.addAttribute("hasOrder", false);
                model.addAttribute("customerId", userService.getIdByUsername(authentication.getName()));
                model.addAttribute("tableId", orderTypeService.getTableIdByName("online"));
                return "home-customer";
            }
            else {
                model.addAttribute("hasOrder", true);
                model.addAttribute("customerId", userService.getIdByUsername(authentication.getName()));
                model.addAttribute("tableId", orderTypeService.getTableIdByName("online"));
                return "home-customer-order";
            }
        }
        return null;
    }

    @GetMapping("/home-customer-order")
    public String customerWithOrder(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("hasOrder", true);
        model.addAttribute("customerId", userService.getIdByUsername(authentication.getName()));
        model.addAttribute("tableId", orderTypeService.getTableIdByName("online"));
        return "home-customer-order";
    }
}
