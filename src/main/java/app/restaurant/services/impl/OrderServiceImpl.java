package app.restaurant.services.impl;

import app.restaurant.models.entities.Meal;
import app.restaurant.models.entities.MealPreparation;
import app.restaurant.models.entities.Order;
import app.restaurant.repositories.OrderRepository;
import app.restaurant.services.MealService;
import app.restaurant.services.OrderService;
import app.restaurant.services.OrderTypeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderTypeService orderTypeService;
    private final MealService mealService;

    public OrderServiceImpl(OrderRepository orderRepository, OrderTypeService orderTypeService, MealService mealService) {
        this.orderRepository = orderRepository;
        this.orderTypeService = orderTypeService;
        this.mealService = mealService;
    }

    @Override
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
        mealsList.add(beer);
        MealPreparation orangeJuice = new MealPreparation();
        orangeJuice.setCount(1);
        orangeJuice.setPrepared(false);
        orangeJuice.setMeal(mealService.getMealByName("Orange Juice"));
        orangeJuice.setNotEnoughIngredients(false);
        mealsList.add(orangeJuice);
        MealPreparation cola = new MealPreparation();
        cola.setCount(1);
        cola.setPrepared(false);
        cola.setMeal(mealService.getMealByName("Coca Cola"));
        cola.setNotEnoughIngredients(false);
        mealsList.add(cola);
        onlyDrinks.setMealList(mealsList);
        orderRepository.save(onlyDrinks);
        }
    }
}
