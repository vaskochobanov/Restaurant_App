package app.restaurant.services;

import app.restaurant.models.bindings.OrderTypeAddBindingModel;
import app.restaurant.models.dtos.OrderTypeViewDto;

import java.util.List;

public interface OrderTypeService {
    boolean addOrderType(OrderTypeAddBindingModel orderTypeAddBindingModel);
    List<OrderTypeViewDto> getAllTables();
}
