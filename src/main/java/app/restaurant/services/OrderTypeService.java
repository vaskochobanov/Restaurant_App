package app.restaurant.services;

import app.restaurant.models.bindings.CustomerAddOrderBindingModel;
import app.restaurant.models.bindings.OrderTypeAddBindingModel;
import app.restaurant.models.bindings.OrderTypeEditBindingModel;
import app.restaurant.models.bindings.WaiterAddOrderBindingModel;
import app.restaurant.models.dtos.*;
import app.restaurant.models.entities.OrderType;

import java.util.List;

public interface OrderTypeService {
    boolean addOrderType(OrderTypeAddBindingModel orderTypeAddBindingModel);
    List<OrderTypeViewDto> getAllTables();
    void deleteTable(Long id);
    OrderTypeViewDto getTableById(Long id);
    void editTable(OrderTypeEditBindingModel orderTypeEditBindingModel);
    OrderType getTableByName(String name);
    void initTables();
    List<OrderTypeWaiterViewDto> getTablesByWaiter(Long waiterId);
    void createNewOrderFromWaiters(WaiterAddOrderBindingModel[] mealsArr);
    void closeOrderOnTable(String tableName);
    List<WaiterMealsInOrder> getAllMealsInOrderForTableId(Long tableId);
    void editOrder(Long tableId, WaiterAddOrderBindingModel[] mealsArr);
    Long getTableIdByName(String tableName);
    void createNewOrderFromCustomer(CustomerAddOrderBindingModel[] mealsArr);
    List<OrderTypeCustomerViewDto> onlineOrderByCustomerId(Long custId);
    boolean checkWaiterForOnline(Long waiterId);
    List<OnlineOrderViewDto> getAllOnlineOrdersInfo();
    void closeOnlineOrder(Long orderId);
}
