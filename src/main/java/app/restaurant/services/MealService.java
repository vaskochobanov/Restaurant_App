package app.restaurant.services;

import app.restaurant.models.bindings.MealAddBindingModel;
import app.restaurant.models.bindings.MealEditBindingModel;
import app.restaurant.models.dtos.MealHomePageViewDto;
import app.restaurant.models.dtos.MealViewDto;
import app.restaurant.models.dtos.MealWaiterViewDto;
import app.restaurant.models.entities.Meal;

import java.util.List;

public interface MealService {
    boolean addMeal(MealAddBindingModel mealAddBindingModel);
    void initMeals();
    List<MealViewDto> getAllMealsSorted();
    void deleteMeal(Long id);
    MealViewDto getMealById(Long id);
    void editMeal(MealEditBindingModel mealEditBindingModel);
    Meal getMealByName(String name);
    List<MealWaiterViewDto> getAllActiveMeals();
    Meal mealById(Long id);
    MealHomePageViewDto getPromotedDish(String dishType);
}
