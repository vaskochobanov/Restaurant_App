package app.restaurant.services.impl;

import app.restaurant.models.bindings.UserRegisterBindingModel;
import app.restaurant.models.entities.User;
import app.restaurant.models.entities.enums.UserRole;
import app.restaurant.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    private UserServiceImpl serviceToTest;
    @Mock
    UserRepository mockUserRepository;
    @Mock
    PasswordEncoder mockPasswordEncoder;
    @Mock
    ModelMapper mockModelMapper;
    @BeforeEach
    public void setUp() {
        serviceToTest = new UserServiceImpl(mockUserRepository, mockPasswordEncoder, mockModelMapper);
    }
    @Test
    public void userNotFound() {
        Assertions.assertThrows(UsernameNotFoundException.class, () -> {
            serviceToTest.loadUserByUsername("user_does_not_exist");
        });
    }
    @Test
    public void testExistingUser() {
        User user = new User();
        user.setUsername("spiro");
        user.setPassword("123456");
        user.setRole(UserRole.CUSTOMER);
        Mockito.when(mockUserRepository.findByUsername("spiro")).thenReturn(java.util.Optional.of(user));
        UserDetails userDetails = serviceToTest.loadUserByUsername("spiro");
        Assertions.assertEquals("spiro", userDetails.getUsername());
        Assertions.assertEquals(1, userDetails.getAuthorities().size());
        List<String> authorities = userDetails.getAuthorities().stream().map(a -> a.getAuthority()).collect(Collectors.toList());
        Assertions.assertTrue(authorities.contains("ROLE_CUSTOMER"));
    }
    @Test
    public void testGetCustomer() {
        User user = new User();
        user.setUsername("spiro");
        user.setPassword("123456");
        user.setRole(UserRole.CUSTOMER);
        Mockito.when(mockUserRepository.findById(2L)).thenReturn(java.util.Optional.of(user));
        Assertions.assertEquals("spiro", serviceToTest.getOnlineUserById(2L).getUsername());
    }
}
