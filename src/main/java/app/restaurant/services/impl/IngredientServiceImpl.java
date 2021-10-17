package app.restaurant.services.impl;

import app.restaurant.models.bindings.IngredientAddBindingModel;
import app.restaurant.models.dtos.IngredientViewDto;
import app.restaurant.models.entities.Ingredient;
import app.restaurant.repositories.IngredientRepository;
import app.restaurant.services.IngredientService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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
            cucumbers.setQuantity(10.0);
            cucumbers.setImageUrl("https://cdn.mos.cms.futurecdn.net/EBEXFvqez44hySrWqNs3CZ.jpg");
            ingredientRepository.save(cucumbers);
            Ingredient tomatoes = new Ingredient();
            tomatoes.setBestBefore(LocalDate.of(2021, 11, 11));
            tomatoes.setName("tomatoes");
            tomatoes.setQuantity(10.0);
            tomatoes.setImageUrl("https://www.vegetamo.it/wp-content/uploads/2018/04/pomodori.jpg");
            ingredientRepository.save(tomatoes);
            Ingredient oliveOil = new Ingredient();
            oliveOil.setBestBefore(LocalDate.of(2022, 10, 17));
            oliveOil.setImageUrl("https://produits.bienmanger.com/18473-0w470h470_Organic_Olive_Oil_Kanakis.jpg");
            oliveOil.setName("olive oil");
            oliveOil.setQuantity(5.0);
            ingredientRepository.save(oliveOil);
            Ingredient fetaCheese = new Ingredient();
            fetaCheese.setBestBefore(LocalDate.of(2021, 12, 17));
            fetaCheese.setImageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/2/28/Feta_Cheese.jpg/1200px-Feta_Cheese.jpg");
            fetaCheese.setName("feta cheese");
            fetaCheese.setQuantity(10.0);
            ingredientRepository.save(fetaCheese);
            Ingredient salt = new Ingredient();
            salt.setBestBefore(LocalDate.of(2024, 10, 17));
            salt.setImageUrl("https://post.medicalnewstoday.com/wp-content/uploads/sites/3/2020/02/322745_1100-732x549.jpg");
            salt.setName("salt");
            salt.setQuantity(5.0);
            ingredientRepository.save(salt);
        }
    }

    @Override
    public List<IngredientViewDto> getAllIngredientsSorted() {
        return ingredientRepository.findAllSortedByExpiryDate().stream().map(i -> modelMapper.map(i, IngredientViewDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteIngredient(Long id) {
        ingredientRepository.deleteById(id);
    }
}
