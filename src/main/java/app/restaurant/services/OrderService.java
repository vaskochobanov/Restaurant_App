package app.restaurant.services;

import app.restaurant.models.dtos.MealPreparationViewDto;
import app.restaurant.models.entities.Order;
import app.restaurant.models.entities.OrderType;
import app.restaurant.models.entities.User;

import java.util.List;

public interface OrderService {
    //void initOrders();
    Order getOpenOrderByTableId(Long tableId);
    Order createNewOrderFromWaiter(OrderType tabble);
    Order getOpenOrderByTableName(String tableName);
    void saveOrder(Order order);
    void deleteOrderById(Long orderId);
    Order createNewOrderFromCustomer(OrderType online, User onlineUser);
    Long getOrderIdByCustomerId(Long customerId);
    List<Order> getActiveOnlineOrders();
    Order getOrderById(Long orderId);
}
