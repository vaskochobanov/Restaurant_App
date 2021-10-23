package app.restaurant.web;

import app.restaurant.models.dtos.IngredientViewDto;
import app.restaurant.models.dtos.MealViewDto;
import app.restaurant.models.dtos.OrderTypeViewDto;
import app.restaurant.models.dtos.UserViewDto;
import app.restaurant.services.IngredientService;
import app.restaurant.services.MealService;
import app.restaurant.services.OrderTypeService;
import app.restaurant.services.UserService;
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
    private final UserService userService;
    private final OrderTypeService orderTypeService;

    public ApiController(MealService mealService, IngredientService ingredientService, UserService userService,
                         OrderTypeService orderTypeService) {
        this.mealService = mealService;
        this.ingredientService = ingredientService;
        this.userService = userService;
        this.orderTypeService = orderTypeService;
    }

    @GetMapping("/meals")
    public ResponseEntity<List<MealViewDto>> loadAllMeals() {
        return ResponseEntity.status(200).body(mealService.getAllMealsSorted());
    }
    @GetMapping("/ingredients")
    public ResponseEntity<List<IngredientViewDto>> loadAllIngredients() {
        return ResponseEntity.status(200).body(ingredientService.getAllIngredientsSorted());
    }
    @GetMapping("/users")
    public ResponseEntity<List<UserViewDto>> loadAllUsers() {
        return ResponseEntity.status(200).body(userService.getAllUsers());
    }
    @GetMapping("/tables")
    public ResponseEntity<List<OrderTypeViewDto>> loadAllTables() {
        return ResponseEntity.status(200).body(orderTypeService.getAllTables());
    }
}
