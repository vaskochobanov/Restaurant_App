package app.restaurant.services.impl;

import app.restaurant.models.dtos.MealPreparationViewDto;
import app.restaurant.models.entities.MealPreparation;
import app.restaurant.models.entities.Order;
import app.restaurant.repositories.MealPreparationRepository;
import app.restaurant.services.MealPreparationService;
import app.restaurant.services.MealService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class MealPreparationServiceImpl implements MealPreparationService {
    private final MealPreparationRepository mealPreparationRepository;
    private final MealService mealService;
    private final ModelMapper modelMapper;

    public MealPreparationServiceImpl(MealPreparationRepository mealPreparationRepository, MealService mealService,
                                      ModelMapper modelMapper) {
        this.mealPreparationRepository = mealPreparationRepository;
        this.mealService = mealService;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public void initMailPreparations(Order order) {
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
    public List<MealPreparationViewDto> getDrinks() {
        List<MealPreparationViewDto> result = new ArrayList<>();
        mealPreparationRepository.findMealsFromOpenOrders().stream().forEach(mp -> {
            if (mp.getMeal().getType().name().equals("DRINK")) {
                MealPreparationViewDto current = modelMapper.map(mp, MealPreparationViewDto.class);
                current.setMealName(mp.getMeal().getName());
                current.setMealIngredients(mp.getMeal().getIngredients());
                current.setOrderId(mp.getOrder().getId());
                result.add(current);
            }
        });
        return result;
    }
}
