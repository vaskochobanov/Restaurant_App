package app.restaurant.services.impl;

import app.restaurant.models.bindings.MealAddBindingModel;
import app.restaurant.models.bindings.MealEditBindingModel;
import app.restaurant.models.dtos.MealHomePageViewDto;
import app.restaurant.models.dtos.MealViewDto;
import app.restaurant.models.dtos.MealWaiterViewDto;
import app.restaurant.models.entities.Ingredient;
import app.restaurant.models.entities.Meal;
import app.restaurant.models.entities.enums.MealType;
import app.restaurant.repositories.MealRepository;
import app.restaurant.services.IngredientService;
import app.restaurant.services.MealService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MealServiceImpl implements MealService {
    private final MealRepository mealRepository;
    private final ModelMapper modelMapper;
    private final IngredientService ingredientService;

    public MealServiceImpl(MealRepository mealRepository, ModelMapper modelMapper, IngredientService ingredientService) {
        this.mealRepository = mealRepository;
        this.modelMapper = modelMapper;
        this.ingredientService = ingredientService;
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
        beer.setImageUrl("/img/beer.jpg");
        beer.setIngredients("beer-0.5");
        beer.setName("Beer");
        beer.setPrice(2.49);
        beer.setType(MealType.DRINK);
        mealRepository.save(beer);
        Meal shopskaSalad = new Meal();
        shopskaSalad.setActive(true);
        shopskaSalad.setPromoted(true);
        shopskaSalad.setDescription("A mixture of cucumbers, tomatoes and feta cheese");
        shopskaSalad.setImageUrl("/img/shopska.jpg");
        shopskaSalad.setIngredients("cucumbers-0.3,tomatoes-0.2,feta cheese-0.15,olive oil-0.03,salt-0.01");
        shopskaSalad.setName("Shopska Salad");
        shopskaSalad.setPrice(8.99);
        shopskaSalad.setType(MealType.SALAD);
        mealRepository.save(shopskaSalad);
        Meal pizza = new Meal();
        pizza.setActive(true);
        pizza.setDescription("A classic italian pizza.");
        pizza.setImageUrl("/img/pizza.jpg");
        pizza.setIngredients("flour-0.1,yeast-0.005,tomato sauce-0.1,ham-0.2,mozzarella-0.1");
        pizza.setName("Pizza");
        pizza.setPrice(10.99);
        pizza.setPromoted(true);
        pizza.setType(MealType.MAIN_DISH);
        mealRepository.save(pizza);
        Meal orangeJuice = new Meal();
        orangeJuice.setActive(true);
        orangeJuice.setDescription("A glass of freshly squeezed orange juice.");
        orangeJuice.setImageUrl("https://greenletes.com/wp-content/uploads/2018/05/orange-juice-1024x1024.png");
        orangeJuice.setIngredients("oranges-0.5");
        orangeJuice.setName("Orange Juice");
        orangeJuice.setPrice(4.99);
        orangeJuice.setPromoted(false);
        orangeJuice.setType(MealType.DRINK);
        mealRepository.save(orangeJuice);
        Meal cola = new Meal();
        cola.setActive(true);
        cola.setDescription("A can of classic Coca Cola");
        cola.setImageUrl("/img/cola.jpg");
        cola.setIngredients("coca cola-0.3");
        cola.setName("Coca Cola");
        cola.setPrice(1.50);
        cola.setPromoted(false);
        cola.setType(MealType.DRINK);
        mealRepository.save(cola);
        Meal frenchSalad = new Meal();
        frenchSalad.setActive(true);
        frenchSalad.setDescription("French salad");
        frenchSalad.setImageUrl("/img/french_salad.jpg");
        frenchSalad.setIngredients("potatoes-0.1,carrots-0.05,corn-0.05,ham-0.05,mayonnaise-0.1");
        frenchSalad.setName("French Salad");
        frenchSalad.setPrice(7.50);
        frenchSalad.setPromoted(false);
        frenchSalad.setType(MealType.SALAD);
        mealRepository.save(frenchSalad);
        Meal risotto = new Meal();
        risotto.setActive(true);
        risotto.setDescription("Imperial rice with frozen vegetables with butter and soy sauce");
        risotto.setImageUrl("/img/risotto.jpg");
        risotto.setIngredients("rice-0.1,frozen vegetables-0.1,butter-0.03,soy sauce-0.01");
        risotto.setName("Risotto");
        risotto.setPrice(7.99);
        risotto.setPromoted(false);
        risotto.setType(MealType.MAIN_DISH);
        mealRepository.save(risotto);
        Meal banica = new Meal();
        banica.setActive(true);
        banica.setDescription("Tradiotional balcanic dish");
        banica.setImageUrl("/img/banica.jpg");
        banica.setIngredients("kori-0.3,feta cheese-0.05,yogurt-0.1,eggs-2");
        banica.setName("Banica");
        banica.setPrice(4.99);
        banica.setPromoted(false);
        banica.setType(MealType.MAIN_DISH);
        mealRepository.save(banica);
        Meal fruitSalad = new Meal();
        fruitSalad.setActive(true);
        fruitSalad.setDescription("A mixture of oranges, bananas, kiwi and strawberries, garnered with cream");
        fruitSalad.setImageUrl("https://cdn.healthyrecipes101.com/wp-content/uploads/2019/11/How-long-will-a-fruit-salad-last-in-the-fridge.jpg");
        fruitSalad.setIngredients("bananas-0.1,oranges-0.1,kiwi-0.05,strawberries-0.05,cream-0.05");
        fruitSalad.setName("Fruit Salad");
        fruitSalad.setPrice(7.99);
        fruitSalad.setPromoted(false);
        fruitSalad.setType(MealType.DESSERT);
        mealRepository.save(fruitSalad);
        Meal brownie = new Meal();
        brownie.setActive(true);
        brownie.setDescription("A classic brownie with a lot of chocolate");
        brownie.setImageUrl("/img/brownie.jpg");
        brownie.setIngredients("flour-0.1,chocolate-0.1,eggs-1,butter-0.05");
        brownie.setName("Brownie");
        brownie.setPrice(4.99);
        brownie.setPromoted(true);
        brownie.setType(MealType.DESSERT);
        mealRepository.save(brownie);
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

    @Override
    public void editMeal(MealEditBindingModel mealEditBindingModel) {
        Meal toEdit = mealRepository.findById(mealEditBindingModel.getId()).orElse(null);
        toEdit.setPrice(mealEditBindingModel.getPrice());
        toEdit.setIngredients(mealEditBindingModel.getIngredients());
        toEdit.setActive(mealEditBindingModel.isActive());
        toEdit.setPromoted(mealEditBindingModel.isPromoted());
        mealRepository.save(toEdit);
    }

    @Override
    public Meal getMealByName(String name) {
        return mealRepository.findByName(name).orElse(null);
    }

    @Override
    public List<MealWaiterViewDto> getAllActiveMeals() {
        List<MealWaiterViewDto> result = new ArrayList<>();
        mealRepository.findAllActiveMeals().stream().forEach(m -> {
            if (m.getType().name().equals("DRINK") && isPossibleToPrepare(m)) {
                MealWaiterViewDto current = modelMapper.map(m, MealWaiterViewDto.class);
                current.setType(m.getType().name());
                result.add(current);
            }
        });
        mealRepository.findAllActiveMeals().stream().forEach(m -> {
            if (m.getType().name().equals("SALAD") && isPossibleToPrepare(m)) {
                MealWaiterViewDto current = modelMapper.map(m, MealWaiterViewDto.class);
                current.setType(m.getType().name());
                result.add(current);
            }
        });
        mealRepository.findAllActiveMeals().stream().forEach(m -> {
            if (m.getType().name().equals("MAIN_DISH") && isPossibleToPrepare(m)) {
                MealWaiterViewDto current = modelMapper.map(m, MealWaiterViewDto.class);
                current.setType(m.getType().name());
                result.add(current);
            }
        });
        mealRepository.findAllActiveMeals().stream().forEach(m -> {
            if (m.getType().name().equals("DESSERT") && isPossibleToPrepare(m)) {
                MealWaiterViewDto current = modelMapper.map(m, MealWaiterViewDto.class);
                current.setType(m.getType().name());
                result.add(current);
            }
        });
        return result;
    }

    @Override
    public Meal mealById(Long id) {
        return mealRepository.findById(id).orElse(null);
    }

    @Override
    public MealHomePageViewDto getPromotedDish(String dishType) {
        return mealRepository.findPromotedMeals().stream().filter(m -> m.getType().name().equals(dishType))
                .map(meal -> modelMapper.map(meal, MealHomePageViewDto.class)).collect(Collectors.toList()).get(0);
    }

    private boolean isPossibleToPrepare(Meal meal) {
        boolean result = true;
        String[] ingredientsArr = meal.getIngredients().split(",");
        for (String parsed : ingredientsArr) {
            String[] parsedArr = parsed.split("-");
            String ingredientName = parsedArr[0];
            Double ingredientQuantity = Double.parseDouble(parsedArr[1]);
            Ingredient toCheck = ingredientService.getIngredientByName(ingredientName);
            if (toCheck == null) {
                result = false;
                break;
            }
            if (toCheck.getQuantity() <= ingredientQuantity * 10) {
                result = false;
                break;
            }
        }
        return result;
    }
}
