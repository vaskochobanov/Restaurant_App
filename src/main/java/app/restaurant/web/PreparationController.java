package app.restaurant.web;

import app.restaurant.services.MealPreparationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PreparationController {
    private final MealPreparationService mealPreparationService;

    public PreparationController(MealPreparationService mealPreparationService) {
        this.mealPreparationService = mealPreparationService;
    }

    @PostMapping("/prep/{id}")
    public String postPrepareMeal(@PathVariable Long id) {
        mealPreparationService.prepareMeal(id);
        return "redirect:/home";
    }
}
