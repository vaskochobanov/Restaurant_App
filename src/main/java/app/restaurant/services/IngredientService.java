package app.restaurant.services;

import app.restaurant.models.bindings.IngredientAddBindingModel;

public interface IngredientService {
    boolean addIngredient(IngredientAddBindingModel ingredientAddBindingModel);
    void initIngredients();
}
