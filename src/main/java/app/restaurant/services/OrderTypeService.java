package app.restaurant.services;

import app.restaurant.models.bindings.OrderTypeAddBindingModel;

public interface OrderTypeService {
    boolean addOrderType(OrderTypeAddBindingModel orderTypeAddBindingModel);
}
