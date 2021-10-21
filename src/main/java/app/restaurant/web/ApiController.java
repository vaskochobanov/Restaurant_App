package app.restaurant.web;

import app.restaurant.models.dtos.MealViewDto;
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

    public ApiController(MealService mealService) {
        this.mealService = mealService;
    }

    @GetMapping("/meals")
    public ResponseEntity<List<MealViewDto>> loadAllMeals() {
        return ResponseEntity.status(200).body(mealService.getAllMealsSorted());
    }
}
