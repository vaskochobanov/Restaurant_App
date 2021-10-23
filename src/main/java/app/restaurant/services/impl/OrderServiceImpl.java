package app.restaurant.services.impl;

import app.restaurant.models.entities.Meal;
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
        Order onlyDrinks = new Order();
        onlyDrinks.setOpen(true);
        onlyDrinks.setOrderType(orderTypeService.getTableByName("t1"));
        List<Meal> meals = new ArrayList<>();
        meals.add(mealService.getMealByName("Beer"));
        meals.add(mealService.getMealByName("Orange Juice"));
        meals.add(mealService.getMealByName("Coca Cola"));
        meals.add(mealService.getMealByName("Beer"));
        onlyDrinks.setMealList(meals);
        orderRepository.save(onlyDrinks);
    }
}
