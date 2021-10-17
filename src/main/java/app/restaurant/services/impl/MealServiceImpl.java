package app.restaurant.services.impl;

import app.restaurant.models.bindings.MealAddBindingModel;
import app.restaurant.models.entities.Ingredient;
import app.restaurant.models.entities.Meal;
import app.restaurant.models.entities.enums.MealType;
import app.restaurant.repositories.MealRepository;
import app.restaurant.services.MealService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MealServiceImpl implements MealService {
    private final MealRepository mealRepository;
    private final ModelMapper modelMapper;

    public MealServiceImpl(MealRepository mealRepository, ModelMapper modelMapper) {
        this.mealRepository = mealRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean addMeal(MealAddBindingModel mealAddBindingModel) {
        Meal tryToFind = mealRepository.findByName(mealAddBindingModel.getName()).orElse(null);
        if (tryToFind != null) {
            return true;
        }
        Meal toAdd = modelMapper.map(mealAddBindingModel, Meal.class);
        toAdd.setType(MealType.valueOf(mealAddBindingModel.getType()));
        mealRepository.save(toAdd);
        return false;
    }
}
