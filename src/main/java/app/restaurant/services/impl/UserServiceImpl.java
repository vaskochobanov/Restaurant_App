package app.restaurant.services.impl;

import app.restaurant.models.bindings.UserLoginBindingModel;
import app.restaurant.models.bindings.UserRegisterBindingModel;
import app.restaurant.models.entities.User;
import app.restaurant.models.entities.enums.UserRole;
import app.restaurant.repositories.UserRepository;
import app.restaurant.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    private UserDetails mapFromUser(User user) {
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_" + user.getRole().name());
        List<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(grantedAuthority);
        UserDetails result = new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), authorityList);
        return result;
    }
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(s)
                .orElseThrow(() -> new UsernameNotFoundException("User with name" + s + "was not found!"));
        return mapFromUser(user);
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

    @Override
    public boolean checkUserExistsInDB(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    @Override
    public void loginUser(UserLoginBindingModel userLoginBindingModel) {
        UserDetails userDetails = this.loadUserByUsername(userLoginBindingModel.getUsername());
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userLoginBindingModel.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
