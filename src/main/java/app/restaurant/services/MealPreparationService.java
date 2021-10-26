package app.restaurant.services;

import app.restaurant.models.dtos.MealPreparationViewDto;
import app.restaurant.models.entities.Order;

import java.util.List;


public interface MealPreparationService {
    void initDrinkMailPreparations(Order order);
    void initSaladMailPreparations(Order order);
    void initMainDishesMailPreparations(Order order);
    List<MealPreparationViewDto> getDrinks();
    List<MealPreparationViewDto> getSalads();
    List<MealPreparationViewDto> getMainDishes();
    void prepareMeal(Long id);
}
