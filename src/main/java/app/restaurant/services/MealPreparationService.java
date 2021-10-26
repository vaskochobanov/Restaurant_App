package app.restaurant.services;

import app.restaurant.models.dtos.MealPreparationViewDto;
import app.restaurant.models.entities.Order;

import java.util.List;


public interface MealPreparationService {
    void initMailPreparations(Order order);
    List<MealPreparationViewDto> getDrinks();
    void prepareMeal(Long id);
}
