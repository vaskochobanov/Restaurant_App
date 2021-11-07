package app.restaurant.services;

import java.util.List;

public interface NeedToBuyService {
    void addToBuy(String ingredientName);
    List<String> getAllUniqueIngredientsToBuy();
    void deleteNeededIngredient(String name);
}
