package app.restaurant.services;

import app.restaurant.models.bindings.MealAddBindingModel;

public interface MealService {
    boolean addMeal(MealAddBindingModel mealAddBindingModel);
    void initMeals();
}
