package app.restaurant.web;

import app.restaurant.models.bindings.CustomerAddOrderBindingModel;
import app.restaurant.models.bindings.WaiterAddOrderBindingModel;
import app.restaurant.models.dtos.*;
import app.restaurant.models.entities.enums.MealType;
import app.restaurant.services.*;
import com.google.gson.Gson;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {
    private final MealService mealService;
    private final IngredientService ingredientService;
    private final UserService userService;
    private final OrderTypeService orderTypeService;
    private final MealPreparationService mealPreparationService;
    private final Gson gson;


    public ApiController(MealService mealService, IngredientService ingredientService, UserService userService,
                         OrderTypeService orderTypeService, MealPreparationService mealPreparationService, Gson gson) {
        this.mealService = mealService;
        this.ingredientService = ingredientService;
        this.userService = userService;
        this.orderTypeService = orderTypeService;
        this.mealPreparationService = mealPreparationService;
        this.gson = gson;
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
        orderTypeService.createNewOrderFromWaiters(gson.fromJson(stringOrder, WaiterAddOrderBindingModel[].class));
    }
    @PostMapping("/close-order")
    public void postWaiterCloseOrder(@RequestBody String data) {
        orderTypeService.closeOrderOnTable(gson.fromJson(data, String.class));
    }
    @GetMapping("/edit-order/{id}")
    public ResponseEntity<List<WaiterMealsInOrder>> getMealsInOrder(@PathVariable Long id) {
        return ResponseEntity.status(200).body(orderTypeService.getAllMealsInOrderForTableId(id));
    }
    @PostMapping("/edit-order/{id}")
    public void postWaiterEditOrder(@PathVariable Long id, @RequestBody String data) {
        orderTypeService.editOrder(id, gson.fromJson(data, WaiterAddOrderBindingModel[].class));
    }
    @PostMapping("/new-order-cust")
    public void postCustomerAddNewOrder(@RequestBody String data) {
        orderTypeService.createNewOrderFromCustomer(gson.fromJson(data, CustomerAddOrderBindingModel[].class));
    }
    @GetMapping("/cust-order/{custId}")
    public ResponseEntity<List<OrderTypeCustomerViewDto>> getOnlineOrderForCustomer(@PathVariable Long custId) {
        return ResponseEntity.status(200).body(orderTypeService.onlineOrderByCustomerId(custId));
    }
    @GetMapping("/waiter-online-orders")
    public ResponseEntity<List<OnlineOrderViewDto>> generateAllOnlineOrders() {
        return ResponseEntity.status(200).body(orderTypeService.getAllOnlineOrdersInfo());
    }
    @PostMapping("/close-online-order")
    public void postWaiterCloseOnlineOrder(@RequestBody String data) {
        orderTypeService.closeOnlineOrder(gson.fromJson(data, Long.class));
    }
    @GetMapping("/ingredients-names")
    public ResponseEntity<List<String>> loadAllIngredientsNames() {
        return ResponseEntity.status(200).body(ingredientService.getUniqueIngredientsNames());
    }
}
