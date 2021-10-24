package app.restaurant.services.impl;

import app.restaurant.models.entities.MealPreparation;
import app.restaurant.models.entities.Order;
import app.restaurant.repositories.OrderRepository;
import app.restaurant.services.MealPreparationService;
import app.restaurant.services.MealService;
import app.restaurant.services.OrderService;
import app.restaurant.services.OrderTypeService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderTypeService orderTypeService;
    private final MealService mealService;
    private final MealPreparationService mealPreparationService;

    public OrderServiceImpl(OrderRepository orderRepository, OrderTypeService orderTypeService, MealService mealService,
                            MealPreparationService mealPreparationService) {
        this.orderRepository = orderRepository;
        this.orderTypeService = orderTypeService;
        this.mealService = mealService;
        this.mealPreparationService = mealPreparationService;
    }

    @Override
    @Transactional
    public void initOrders() {
        if (orderRepository.count() == 0) {
        Order onlyDrinks = new Order();
        onlyDrinks.setOpen(true);
        onlyDrinks.setOrderType(orderTypeService.getTableByName("t1"));
        List<MealPreparation> mealsList = new ArrayList<>();
        MealPreparation beer = new MealPreparation();
        beer.setCount(2);
        beer.setPrepared(false);
        beer.setMeal(mealService.getMealByName("Beer"));
        beer.setNotEnoughIngredients(false);
        mealPreparationService.saveMealPreparation(beer);
        mealsList.add(beer);
        MealPreparation orangeJuice = new MealPreparation();
        orangeJuice.setCount(1);
        orangeJuice.setPrepared(false);
        orangeJuice.setMeal(mealService.getMealByName("Orange Juice"));
        orangeJuice.setNotEnoughIngredients(false);
        mealPreparationService.saveMealPreparation(orangeJuice);
        mealsList.add(orangeJuice);
        MealPreparation cola = new MealPreparation();
        cola.setCount(1);
        cola.setPrepared(false);
        cola.setMeal(mealService.getMealByName("Coca Cola"));
        cola.setNotEnoughIngredients(false);
        mealPreparationService.saveMealPreparation(cola);
        mealsList.add(cola);
        onlyDrinks.setMealList(mealsList);
        orderRepository.save(onlyDrinks);
        Order toCheck = orderRepository.findById(1L).orElse(null);
        System.out.println();
        }
    }
}
