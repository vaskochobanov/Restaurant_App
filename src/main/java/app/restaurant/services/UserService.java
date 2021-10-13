package app.restaurant.services;

import app.restaurant.models.bindings.UserRegisterBindingModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    void initUsers();
    boolean registerCustomerUser(UserRegisterBindingModel userRegisterBindingModel);
}
