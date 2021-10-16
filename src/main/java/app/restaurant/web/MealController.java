package app.restaurant.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/meals")
public class MealController {
    @GetMapping("/add")
    public String getAddMeal() {
        return "admin-add-meal";
    }
}
