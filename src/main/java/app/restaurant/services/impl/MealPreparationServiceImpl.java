package app.restaurant.services.impl;

import app.restaurant.models.dtos.MealPreparationViewDto;
import app.restaurant.models.dtos.MealPreparationWaiterViewDto;
import app.restaurant.models.entities.Ingredient;
import app.restaurant.models.entities.Meal;
import app.restaurant.models.entities.MealPreparation;
import app.restaurant.models.entities.Order;
import app.restaurant.models.entities.enums.MealType;
import app.restaurant.repositories.MealPreparationRepository;
import app.restaurant.services.IngredientService;
import app.restaurant.services.MealPreparationService;
import app.restaurant.services.MealService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MealPreparationServiceImpl implements MealPreparationService {
    private final MealPreparationRepository mealPreparationRepository;
    private final MealService mealService;
    private final ModelMapper modelMapper;
    private final IngredientService ingredientService;

    public MealPreparationServiceImpl(MealPreparationRepository mealPreparationRepository, MealService mealService,
                                      ModelMapper modelMapper, IngredientService ingredientService) {
        this.mealPreparationRepository = mealPreparationRepository;
        this.mealService = mealService;
        this.modelMapper = modelMapper;
        this.ingredientService = ingredientService;
    }

    @Override
    @Transactional
    public void initDrinkMealPreparations(Order order) {
        MealPreparation beer = new MealPreparation();
        beer.setCount(2);
        beer.setPrepared(false);
        beer.setMeal(mealService.getMealByName("Beer"));
        beer.setNotEnoughIngredients(false);
        beer.setOrder(order);
        mealPreparationRepository.save(beer);
        MealPreparation orangeJuice = new MealPreparation();
        orangeJuice.setCount(1);
        orangeJuice.setPrepared(false);
        orangeJuice.setMeal(mealService.getMealByName("Orange Juice"));
        orangeJuice.setNotEnoughIngredients(false);
        orangeJuice.setOrder(order);
        mealPreparationRepository.save(orangeJuice);
        MealPreparation cola = new MealPreparation();
        cola.setCount(1);
        cola.setPrepared(false);
        cola.setMeal(mealService.getMealByName("Coca Cola"));
        cola.setNotEnoughIngredients(false);
        cola.setOrder(order);
        mealPreparationRepository.save(cola);
    }

    @Override
    public void initSaladMealPreparations(Order order) {
        MealPreparation shopska = new MealPreparation();
        shopska.setCount(1);
        shopska.setPrepared(false);
        shopska.setMeal(mealService.getMealByName("Shopska Salad"));
        shopska.setNotEnoughIngredients(false);
        shopska.setOrder(order);
        mealPreparationRepository.save(shopska);
        MealPreparation french = new MealPreparation();
        french.setCount(1);
        french.setMeal(mealService.getMealByName("French Salad"));
        french.setNotEnoughIngredients(false);
        french.setPrepared(false);
        french.setOrder(order);
        mealPreparationRepository.save(french);
    }

    @Override
    public void initMainDishesMealPreparations(Order order) {
        MealPreparation risotto = new MealPreparation();
        risotto.setCount(1);
        risotto.setPrepared(false);
        risotto.setMeal(mealService.getMealByName("Risotto"));
        risotto.setNotEnoughIngredients(false);
        risotto.setOrder(order);
        mealPreparationRepository.save(risotto);
        MealPreparation banica = new MealPreparation();
        banica.setCount(1);
        banica.setPrepared(false);
        banica.setMeal(mealService.getMealByName("Banica"));
        banica.setNotEnoughIngredients(false);
        banica.setOrder(order);
        mealPreparationRepository.save(banica);
    }

    @Override
    public void initDessertsMealPreparations(Order order) {
        MealPreparation fruitSalad = new MealPreparation();
        fruitSalad.setCount(1);
        fruitSalad.setPrepared(false);
        fruitSalad.setMeal(mealService.getMealByName("Fruit Salad"));
        fruitSalad.setNotEnoughIngredients(false);
        fruitSalad.setOrder(order);
        mealPreparationRepository.save(fruitSalad);
        MealPreparation brownie = new MealPreparation();
        brownie.setCount(2);
        brownie.setPrepared(false);
        brownie.setMeal(mealService.getMealByName("Brownie"));
        brownie.setNotEnoughIngredients(false);
        brownie.setOrder(order);
        mealPreparationRepository.save(brownie);
    }

    @Override
    public List<MealPreparationViewDto> getMeals(MealType mealType) {
        List<MealPreparationViewDto> result = new ArrayList<>();
        mealPreparationRepository.findMealsFromOpenOrders().stream().forEach(mp -> {
            if (mp.getMeal().getType().name().equals(mealType.name()) && !mp.isPrepared()) {
                MealPreparationViewDto current = modelMapper.map(mp, MealPreparationViewDto.class);
                current.setMealName(mp.getMeal().getName());
                current.setMealIngredients(mp.getMeal().getIngredients());
                current.setOrderId(mp.getOrder().getId());
                result.add(current);
            }
        });
        return result;
    }

    @Override
    public void prepareMeal(Long id) throws NullPointerException {
        MealPreparation forPrepare = mealPreparationRepository.findById(id).orElse(null);
        Arrays.stream(forPrepare.getMeal().getIngredients().split(",")).forEach(i -> {
            //in this array in position 0 is ingredient name and on position 1 is quantity for this meal
            String[] currentIngredientArr = i.split("-");
            String currentIngredientName = currentIngredientArr[0];
            Double currentIngredientQuantity = Double.parseDouble(currentIngredientArr[1]);
            Double quantityNeeded = forPrepare.getCount() * currentIngredientQuantity;
            while (quantityNeeded > 0) {
                Ingredient currentIngredient = ingredientService.getIngredientByName(currentIngredientName);
                if (currentIngredient != null) {
                    if (quantityNeeded < currentIngredient.getQuantity()) {
                        currentIngredient.setQuantity(currentIngredient.getQuantity() - quantityNeeded);
                        ingredientService.updateIngredient(currentIngredient);
                        quantityNeeded = 0.0;
                    }
                    else {
                        quantityNeeded -= currentIngredient.getQuantity();
                        ingredientService.deleteIngredient(currentIngredient.getId());
                    }
                }
                else {
                    forPrepare.setNotEnoughIngredients(true);
                    break;
                }
            }
            forPrepare.setPrepared(true);
            mealPreparationRepository.save(forPrepare);
        });
    }

    @Override
    public List<MealPreparationWaiterViewDto> getMealPreparationsbyOrderId(Long orderId) {
        List<MealPreparationWaiterViewDto> result = new ArrayList<>();
        mealPreparationRepository.findAllByOrderId(orderId).stream().forEach(mp -> {
            MealPreparationWaiterViewDto current = modelMapper.map(mp, MealPreparationWaiterViewDto.class);
            current.setOrderId(mp.getOrder().getId());
            result.add(current);
        });
        return result;
    }

    @Override
    public Double getSumOfOrderId(Long orderId) {
        return mealPreparationRepository.findSumOfMealsInOrder(orderId);
    }

    @Override
    public void createNewMealPreparationFromWaiter(Order order, Long mealId, Integer count) {
        Meal meal = mealService.mealById(mealId);
        MealPreparation toAdd = new MealPreparation();
        toAdd.setCount(count);
        toAdd.setMeal(meal);
        toAdd.setNotEnoughIngredients(false);
        toAdd.setPrepared(false);
        toAdd.setOrder(order);
        mealPreparationRepository.save(toAdd);
    }

    @Override
    public void deleteMealPreparationById(Long mealId) {
        mealPreparationRepository.deleteById(mealId);
    }
}
