package app.restaurant.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PreparationController {
    @PostMapping("/prep/{id}")
    public String postPrepareMeal(@PathVariable Long id) {
        return "redirect:/home";
    }
}
