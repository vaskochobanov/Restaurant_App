package app.restaurant.services;

import app.restaurant.models.bindings.IngredientAddBindingModel;
import app.restaurant.models.dtos.IngredientViewDto;
import app.restaurant.models.entities.Ingredient;

import java.util.List;

public interface IngredientService {
    void addIngredient(IngredientAddBindingModel ingredientAddBindingModel);
    void initIngredients();
    List<IngredientViewDto> getAllIngredientsSorted();
    void deleteIngredient(Long id);
    Ingredient getIngredientByName(String name);
    void updateIngredient(Ingredient ingredient);
    List<String> getUniqueIngredientsNames();
}
