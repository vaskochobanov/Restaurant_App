package app.restaurant.services;

import app.restaurant.models.dtos.MealPreparationViewDto;
import app.restaurant.models.entities.Order;
import app.restaurant.models.entities.OrderType;

import java.util.List;

public interface OrderService {
    //void initOrders();
    Order getOpenOrderByTableId(Long tableId);
    Order createNewOrderFromWaiter(OrderType tabble);
    Order getOpenOrderByTableName(String tableName);
    void saveOrder(Order order);
}
