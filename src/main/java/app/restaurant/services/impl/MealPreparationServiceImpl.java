package app.restaurant.services.impl;

import app.restaurant.models.dtos.MealPreparationViewDto;
import app.restaurant.models.entities.Ingredient;
import app.restaurant.models.entities.MealPreparation;
import app.restaurant.models.entities.Order;
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
    public void initDrinkMailPreparations(Order order) {
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
    public void initSaladMailPreparations(Order order) {
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
    public void initMainDishesMailPreparations(Order order) {
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
    public List<MealPreparationViewDto> getDrinks() {
        List<MealPreparationViewDto> result = new ArrayList<>();
        mealPreparationRepository.findMealsFromOpenOrders().stream().forEach(mp -> {
            if (mp.getMeal().getType().name().equals("DRINK") && !mp.isPrepared()) {
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
    public List<MealPreparationViewDto> getSalads() {
        List<MealPreparationViewDto> result = new ArrayList<>();
        mealPreparationRepository.findMealsFromOpenOrders().stream().forEach(mp -> {
            if (mp.getMeal().getType().name().equals("SALAD") && !mp.isPrepared()) {
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
    public List<MealPreparationViewDto> getMainDishes() {
        List<MealPreparationViewDto> result = new ArrayList<>();
        mealPreparationRepository.findMealsFromOpenOrders().stream().forEach(mp -> {
            if (mp.getMeal().getType().name().equals("MAIN_DISH") && !mp.isPrepared()) {
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
                    throw new NullPointerException("Ingredient does not exist in storage");
                }
            }
            forPrepare.setPrepared(true);
            mealPreparationRepository.save(forPrepare);
        });
    }
}
