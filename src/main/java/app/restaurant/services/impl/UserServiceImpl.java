package app.restaurant.services.impl;

import app.restaurant.models.bindings.UserRegisterBindingModel;
import app.restaurant.models.entities.User;
import app.restaurant.models.entities.enums.UserRole;
import app.restaurant.repositories.UserRepository;
import app.restaurant.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    @Override
    public void initUsers() {
        //Create and store ADMIN user
        if (userRepository.count() == 0) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("123456"));
            admin.setRole(UserRole.ADMIN);
            userRepository.save(admin);
        }
    }

    @Override
    public boolean registerCustomerUser(UserRegisterBindingModel userRegisterBindingModel) {
        User tryToFind = userRepository.findByUsername(userRegisterBindingModel.getUsername()).orElse(null);
        if (tryToFind != null) {
            return true;
        }
        User userToAdd = modelMapper.map(userRegisterBindingModel, User.class);
        userToAdd.setRole(UserRole.CUSTOMER);
        userRepository.save(userToAdd);
        return false;
    }
}
