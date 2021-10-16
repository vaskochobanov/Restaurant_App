package app.restaurant.services;

import app.restaurant.models.bindings.IngredientAddBindingModel;

public interface IngredientService {
    void addIngredient(IngredientAddBindingModel ingredientAddBindingModel);
    void initIngredients();
}
