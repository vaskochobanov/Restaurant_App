package app.restaurant.services.impl;

import app.restaurant.models.entities.Order;
import app.restaurant.models.entities.OrderType;
import app.restaurant.repositories.OrderRepository;
import app.restaurant.services.MealPreparationService;
import app.restaurant.services.OrderService;
import app.restaurant.services.OrderTypeService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
//        this.orderTypeService = orderTypeService;
//        this.mealPreparationService = mealPreparationService;
    }

//    @Override
//    @Transactional
//    public void initOrders() {
//        if (orderRepository.count() == 0) {
//        Order onlyDrinks = new Order();
//        onlyDrinks.setOpen(true);
//        onlyDrinks.setOrderType(orderTypeService.getTableByName("t1"));
//        mealPreparationService.initDrinkMealPreparations(orderRepository.save(onlyDrinks));
//        Order onlySalads = new Order();
//        onlySalads.setOpen(true);
//        onlySalads.setOrderType(orderTypeService.getTableByName("t2"));
//        mealPreparationService.initSaladMealPreparations(orderRepository.save(onlySalads));
//        Order onlyMainDishes = new Order();
//        onlyMainDishes.setOpen(true);
//        onlyMainDishes.setOrderType(orderTypeService.getTableByName("t1"));
//        mealPreparationService.initMainDishesMealPreparations(orderRepository.save(onlyMainDishes));
//        Order onlyDesserts = new Order();
//        onlyDesserts.setOpen(true);
//        onlyDesserts.setOrderType(orderTypeService.getTableByName("t2"));
//        mealPreparationService.initDessertsMealPreparations(orderRepository.save(onlyDesserts));
//        }
//    }

    @Override
    public Order getOpenOrderByTableId(Long tableId) {
        return orderRepository.findOpenOrderByTableId(tableId).orElse(null);
    }

    @Override
    public Order createNewOrderFromWaiter(OrderType table) {
        Order toAdd = new Order();
        toAdd.setOpen(true);
        toAdd.setOrderType(table);
        return orderRepository.save(toAdd);
    }

    @Override
    public Order getOpenOrderByTableName(String tableName) {
        return orderRepository.findOpenOrderByTableName(tableName).orElse(null);
    }

    @Override
    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    @Override
    public void deleteOrderById(Long orderId) {
        orderRepository.deleteById(orderId);
    }
}
