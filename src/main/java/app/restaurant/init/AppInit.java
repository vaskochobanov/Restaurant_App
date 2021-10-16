package app.restaurant.init;

import app.restaurant.services.IngredientService;
import app.restaurant.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppInit implements CommandLineRunner {
    private final UserService userService;
    private final IngredientService ingredientService;

    public AppInit(UserService userService, IngredientService ingredientService) {
        this.userService = userService;
        this.ingredientService = ingredientService;
    }

    @Override
    public void run(String... args) throws Exception {
        userService.initUsers();
        ingredientService.initIngredients();
    }
}
