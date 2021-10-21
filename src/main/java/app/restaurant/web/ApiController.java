package app.restaurant.web;

import app.restaurant.models.dtos.IngredientViewDto;
import app.restaurant.models.dtos.MealViewDto;
import app.restaurant.services.IngredientService;
import app.restaurant.services.MealService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {
    private final MealService mealService;
    private final IngredientService ingredientService;

    public ApiController(MealService mealService, IngredientService ingredientService) {
        this.mealService = mealService;
        this.ingredientService = ingredientService;
    }

    @GetMapping("/meals")
    public ResponseEntity<List<MealViewDto>> loadAllMeals() {
        return ResponseEntity.status(200).body(mealService.getAllMealsSorted());
    }
    @GetMapping("/ingredients")
    public ResponseEntity<List<IngredientViewDto>> loadAllIngredients() {
        return ResponseEntity.status(200).body(ingredientService.getAllIngredientsSorted());
    }
}
