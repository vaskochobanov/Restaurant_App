package app.restaurant.services.impl;

import app.restaurant.models.bindings.IngredientAddBindingModel;
import app.restaurant.models.entities.Ingredient;
import app.restaurant.repositories.IngredientRepository;
import app.restaurant.services.IngredientService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class IngredientServiceImpl implements IngredientService {
    private final IngredientRepository ingredientRepository;
    private final ModelMapper modelMapper;

    public IngredientServiceImpl(IngredientRepository ingredientRepository, ModelMapper modelMapper) {
        this.ingredientRepository = ingredientRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addIngredient(IngredientAddBindingModel ingredientAddBindingModel) {
        Ingredient toAdd = modelMapper.map(ingredientAddBindingModel, Ingredient.class);
        toAdd.setName(ingredientAddBindingModel.getName().toLowerCase());
        ingredientRepository.save(toAdd);
    }

    @Override
    public void initIngredients() {
        if (ingredientRepository.count() == 0) {
            Ingredient cucumbers = new Ingredient();
            cucumbers.setBestBefore(LocalDate.of(2021, 11, 11));
            cucumbers.setName("cucumbers");
            cucumbers.setQuantity(10);
            ingredientRepository.save(cucumbers);
            Ingredient tomatoes = new Ingredient();
            tomatoes.setBestBefore(LocalDate.of(2021, 11, 11));
            tomatoes.setName("cucumbers");
            tomatoes.setQuantity(10);
            ingredientRepository.save(tomatoes);
        }
    }
}
