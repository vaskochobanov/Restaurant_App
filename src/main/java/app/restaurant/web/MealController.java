package app.restaurant.web;

import app.restaurant.models.bindings.MealAddBindingModel;
import app.restaurant.models.bindings.MealEditBindingModel;
import app.restaurant.models.entities.enums.MealType;
import app.restaurant.services.MealService;
import app.restaurant.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    private final UserService userService;

    public MealController(MealService mealService, UserService userService) {
        this.mealService = mealService;
        this.userService = userService;
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

    @GetMapping("/edit")
    public String getEditListMeals(Model model) {
        model.addAttribute("meals", mealService.getAllMealsSorted());
        return "admin-edit-meals";
    }

    @GetMapping("/delete/{id}")
    public String deleteMeal(@PathVariable Long id) {
        mealService.deleteMeal(id);
        return "redirect:/meals/edit";
    }

    @GetMapping("/single-edit/{id}")
    public String getSingleMealEdit(@PathVariable Long id, Model model) {
        model.addAttribute("mealForEdit", mealService.getMealById(id));
        if (!model.containsAttribute("hasErrors")) {
            model.addAttribute("hasErrors", false);
        }
        if (!model.containsAttribute("priceNotValid")) {
            model.addAttribute("priceNotValid", false);
        }
        if (!model.containsAttribute("ingredientsNotValid")) {
            model.addAttribute("ingredientsNotValid", false);
        }
        return "edit-single-meal";
    }

    @PostMapping("/single-edit/{id}")
    public String postEditMeal(@PathVariable Long id, MealEditBindingModel mealEditBindingModel,
                               RedirectAttributes redirectAttributes) {
        if (mealEditBindingModel.getPrice() > 0 && mealEditBindingModel.getIngredients().length() > 2) {
            mealEditBindingModel.setId(id);
            mealService.editMeal(mealEditBindingModel);
            return "redirect:/meals/edit";
        }
        if (mealEditBindingModel.getPrice() <= 0) {
            redirectAttributes.addFlashAttribute("priceNotValid", true);
        }
        if (mealEditBindingModel.getIngredients().length() < 3) {
            redirectAttributes.addFlashAttribute("ingredientsNotValid", true);
        }
        redirectAttributes.addFlashAttribute("hasErrors", true);
        return String.format("redirect:/meals/single-edit/%s", id);
    }
    @GetMapping("/meals-menu/{id}")
    public String getMenu(@PathVariable Long id, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("waiterId", userService.getIdByUsername(authentication.getName()));
        model.addAttribute("tableId", id);
        return "waiter-menu";
    }
}
