package app.restaurant.web;

import app.restaurant.models.bindings.IngredientAddBindingModel;
import app.restaurant.services.IngredientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("/add")
    public String getAddIngredient(Model model) {
        if (!model.containsAttribute("ingredientAddBindingModel")) {
            model.addAttribute("ingredientAddBindingModel", new IngredientAddBindingModel());
            model.addAttribute("ingredientExists", false);
        }
        return "admin-add-ingredient";
    }
    @PostMapping("/add")
    public String postAddIngredient(@Valid IngredientAddBindingModel ingredientAddBindingModel, BindingResult bindingResult,
                                    RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("ingredientAddBindingModel", ingredientAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.ingredientAddBindingModel",
                    bindingResult);
            redirectAttributes.addFlashAttribute("ingredientExists", false);
            return "redirect:add";
        }
        boolean ingredientExists = ingredientService.addIngredient(ingredientAddBindingModel);
        if (ingredientExists) {
            redirectAttributes.addFlashAttribute("ingredientAddBindingModel", ingredientAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.ingredientAddBindingModel",
                    bindingResult);
            redirectAttributes.addFlashAttribute("ingredientExists", true);
            return "redirect:add";
        }
        return "redirect:/home";
    }
}
