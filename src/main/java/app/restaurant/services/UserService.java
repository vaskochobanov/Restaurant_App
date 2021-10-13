package app.restaurant.services;

import app.restaurant.models.bindings.UserRegisterBindingModel;

public interface UserService {
    void initUsers();
    boolean registerCustomerUser(UserRegisterBindingModel userRegisterBindingModel);
}
