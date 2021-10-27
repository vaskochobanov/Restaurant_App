package app.restaurant.init;

import app.restaurant.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppInit implements CommandLineRunner {
    private final UserService userService;
    private final IngredientService ingredientService;
    private final MealService mealService;
    private final OrderTypeService orderTypeService;
    private final OrderService orderService;

    public AppInit(UserService userService, IngredientService ingredientService, MealService mealService,
                   OrderTypeService orderTypeService, OrderService orderService) {
        this.userService = userService;
        this.ingredientService = ingredientService;
        this.mealService = mealService;
        this.orderTypeService = orderTypeService;
        this.orderService = orderService;
    }

    @Override
    public void run(String... args) throws Exception {
        userService.initUsers();
        ingredientService.initIngredients();
        mealService.initMeals();
        orderTypeService.initTables();
        //orderService.initOrders();
    }
}
