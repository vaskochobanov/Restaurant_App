package app.restaurant.services;

import app.restaurant.models.bindings.OrderTypeAddBindingModel;
import app.restaurant.models.bindings.OrderTypeEditBindingModel;
import app.restaurant.models.dtos.OrderTypeViewDto;
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
}
