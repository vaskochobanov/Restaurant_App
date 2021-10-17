package app.restaurant.web;

import app.restaurant.models.bindings.MealAddBindingModel;
import app.restaurant.models.entities.enums.MealType;
import app.restaurant.services.MealService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/meals")
public class MealController {
    private final MealService mealService;

    public MealController(MealService mealService) {
        this.mealService = mealService;
    }

    @GetMapping("/add")
    public String getAddMeal(Model model) {
        model.addAttribute("types", Arrays.stream(MealType.values()).map(t -> t.name()).collect(Collectors.toList()));
        if (!model.containsAttribute("mealAddBindingModel")) {
            model.addAttribute("mealAddBindingModel", new MealAddBindingModel());
            model.addAttribute("mealExists", false);
        }
        return "admin-add-meal";
    }
    @PostMapping("/add")
    public String postAddMeal(@Valid MealAddBindingModel mealAddBindingModel, BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("mealAddBindingModel", mealAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.mealAddBindingModel",
                    bindingResult);
            redirectAttributes.addFlashAttribute("mealExists", false);
            return "redirect:add";
        }
        boolean mealExists = mealService.addMeal(mealAddBindingModel);
        if (mealExists) {
            redirectAttributes.addFlashAttribute("mealAddBindingModel", mealAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.mealAddBindingModel",
                    bindingResult);
            redirectAttributes.addFlashAttribute("mealExists", true);
            return "redirect:add";
        }
        return "redirect:/home";
    }
}
