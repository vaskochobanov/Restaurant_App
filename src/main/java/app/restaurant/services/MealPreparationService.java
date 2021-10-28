package app.restaurant.services;

import app.restaurant.models.dtos.MealPreparationViewDto;
import app.restaurant.models.dtos.MealPreparationWaiterViewDto;
import app.restaurant.models.entities.MealPreparation;
import app.restaurant.models.entities.Order;
import app.restaurant.models.entities.enums.MealType;

import java.util.List;


public interface MealPreparationService {
    void initDrinkMealPreparations(Order order);
    void initSaladMealPreparations(Order order);
    void initMainDishesMealPreparations(Order order);
    void initDessertsMealPreparations(Order order);
    List<MealPreparationViewDto> getMeals(MealType mealType);
    void prepareMeal(Long id);
    List<MealPreparationWaiterViewDto> getMealPreparationsbyOrderId(Long orderId);
    Double getSumOfOrderId(Long orderId);
}
