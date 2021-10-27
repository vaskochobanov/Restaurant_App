package app.restaurant.services;

import app.restaurant.models.dtos.MealPreparationViewDto;
import app.restaurant.models.entities.Order;

import java.util.List;


public interface MealPreparationService {
    void initDrinkMealPreparations(Order order);
    void initSaladMealPreparations(Order order);
    void initMainDishesMealPreparations(Order order);
    void initDessertsMealPreparations(Order order);
    List<MealPreparationViewDto> getDrinks();
    List<MealPreparationViewDto> getSalads();
    List<MealPreparationViewDto> getMainDishes();
    List<MealPreparationViewDto> getDesserts();
    void prepareMeal(Long id);
}
