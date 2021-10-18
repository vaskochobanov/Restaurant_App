package app.restaurant.services.impl;

import app.restaurant.models.bindings.MealAddBindingModel;
import app.restaurant.models.dtos.MealViewDto;
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

    @Override
    public void initMeals() {
        if (mealRepository.count() == 0) {
        Meal beer = new Meal();
        beer.setActive(true);
        beer.setDescription("A 500ml glass of draft beer.");
        beer.setImageUrl("https://thumbs.dreamstime.com/b/glass-draft-beer-isolated-white-background-171472670.jpg");
        beer.setIngredients("beer-1");
        beer.setName("Beer");
        beer.setPrice(2.49);
        beer.setType(MealType.DRINK);
        mealRepository.save(beer);
        Meal shopskaSalad = new Meal();
        shopskaSalad.setActive(false);
        shopskaSalad.setDescription("A mixture of cucumbers, tomatoes and feta cheese");
        shopskaSalad.setImageUrl("https://www.wandercooks.com/wp-content/uploads/2019/07/bulgarian-shopska-salad-ft2.jpg");
        shopskaSalad.setIngredients("cucumbers-0.3,tomatoes-0.2,feta cheese-0.15,olive oil-0.03,salt-0.01");
        shopskaSalad.setName("Shopska Salad");
        shopskaSalad.setPrice(8.99);
        shopskaSalad.setType(MealType.SALAD);
        mealRepository.save(shopskaSalad);
        }
    }

    @Override
    public List<MealViewDto> getAllMealsSorted() {
        return mealRepository.findAllMealsSorted().stream().map(m -> modelMapper.map(m, MealViewDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteMeal(Long id) {
        mealRepository.deleteById(id);
    }

    @Override
    public MealViewDto getMealById(Long id) {
        return modelMapper.map(mealRepository.findById(id).orElse(null), MealViewDto.class);
    }
}
