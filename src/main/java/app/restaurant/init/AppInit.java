package app.restaurant.init;

import app.restaurant.services.IngredientService;
import app.restaurant.services.MealService;
import app.restaurant.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppInit implements CommandLineRunner {
    private final UserService userService;
    private final IngredientService ingredientService;
    private final MealService mealService;

    public AppInit(UserService userService, IngredientService ingredientService, MealService mealService) {
        this.userService = userService;
        this.ingredientService = ingredientService;
        this.mealService = mealService;
    }

    @Override
    public void run(String... args) throws Exception {
        userService.initUsers();
        ingredientService.initIngredients();
        mealService.initMeals();
    }
}
