package app.restaurant.web;

import app.restaurant.models.dtos.*;
import app.restaurant.models.entities.enums.MealType;
import app.restaurant.services.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {
    private final MealService mealService;
    private final IngredientService ingredientService;
    private final UserService userService;
    private final OrderTypeService orderTypeService;
    private final MealPreparationService mealPreparationService;


    public ApiController(MealService mealService, IngredientService ingredientService, UserService userService,
                         OrderTypeService orderTypeService, MealPreparationService mealPreparationService) {
        this.mealService = mealService;
        this.ingredientService = ingredientService;
        this.userService = userService;
        this.orderTypeService = orderTypeService;
        this.mealPreparationService = mealPreparationService;
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
    @GetMapping("/drinks")
    public ResponseEntity<List<MealPreparationViewDto>> loadAllDrinks() {
        return ResponseEntity.status(200).body(mealPreparationService.getMeals(MealType.DRINK));
    }
    @GetMapping("/salads")
    public ResponseEntity<List<MealPreparationViewDto>> loadAllSalads() {
        return ResponseEntity.status(200).body(mealPreparationService.getMeals(MealType.SALAD));
    }
    @GetMapping("/main-dish")
    public ResponseEntity<List<MealPreparationViewDto>> loadAllMainDishes() {
        return ResponseEntity.status(200).body(mealPreparationService.getMeals(MealType.MAIN_DISH));
    }
    @GetMapping("/desserts")
    public ResponseEntity<List<MealPreparationViewDto>> loadAllDesserts() {
        return ResponseEntity.status(200).body(mealPreparationService.getMeals(MealType.DESSERT));
    }
    @GetMapping("/waiter-home/{id}")
    public ResponseEntity<List<OrderTypeWaiterViewDto>> loadAllForWaiter(@PathVariable Long id) {
        return ResponseEntity.status(200).body(orderTypeService.getTablesByWaiter(id));
    }
    @GetMapping("/waiter-menu")
    public ResponseEntity<List<MealWaiterViewDto>> loadWaiterMenu() {
        return ResponseEntity.status(200).body(mealService.getAllActiveMeals());
    }
    @PostMapping("/new-order")
    public void postWaiterAddNewOrder(@RequestBody String stringOrder) {

    }
}
