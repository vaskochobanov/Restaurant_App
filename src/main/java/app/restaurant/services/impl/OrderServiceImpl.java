package app.restaurant.services.impl;

import app.restaurant.models.entities.Order;
import app.restaurant.repositories.OrderRepository;
import app.restaurant.services.MealPreparationService;
import app.restaurant.services.OrderService;
import app.restaurant.services.OrderTypeService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderTypeService orderTypeService;
    private final MealPreparationService mealPreparationService;

    public OrderServiceImpl(OrderRepository orderRepository, OrderTypeService orderTypeService,
                            MealPreparationService mealPreparationService) {
        this.orderRepository = orderRepository;
        this.orderTypeService = orderTypeService;
        this.mealPreparationService = mealPreparationService;
    }

    @Override
    @Transactional
    public void initOrders() {
        if (orderRepository.count() == 0) {
        Order onlyDrinks = new Order();
        onlyDrinks.setOpen(true);
        onlyDrinks.setOrderType(orderTypeService.getTableByName("t1"));
        mealPreparationService.initMailPreparations(orderRepository.save(onlyDrinks));
        }
    }
}
