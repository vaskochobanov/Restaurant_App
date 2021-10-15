package app.restaurant.services;

import app.restaurant.models.bindings.UserAdminRegisterBindingModel;
import app.restaurant.models.bindings.UserRegisterBindingModel;
import app.restaurant.models.dtos.UserEditRoleViewDto;
import app.restaurant.models.dtos.UserViewDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    void initUsers();
    boolean registerCustomerUser(UserRegisterBindingModel userRegisterBindingModel);
    boolean adminRegisterUser(UserAdminRegisterBindingModel userAdminRegisterBindingModel);
    List<UserViewDto> getAllUsers();
    void deleteUser(Long id);
    UserEditRoleViewDto getUserById(Long id);
}
