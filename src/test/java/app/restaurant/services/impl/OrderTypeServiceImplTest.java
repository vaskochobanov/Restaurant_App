package app.restaurant.services.impl;

import app.restaurant.models.entities.OrderType;
import app.restaurant.models.entities.User;
import app.restaurant.models.entities.enums.UserRole;
import app.restaurant.repositories.OrderTypeRepository;
import app.restaurant.services.MealPreparationService;
import app.restaurant.services.OrderService;
import app.restaurant.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

@ExtendWith(MockitoExtension.class)
public class OrderTypeServiceImplTest {
    private OrderTypeServiceImpl serviceToTest;
    @Mock
    OrderTypeRepository mockOrderTypeRepository;
    @Mock
    ModelMapper mockModelMapper;
    @Mock
    UserService mockUserService;
    @Mock
    OrderService mockOrderService;
    @Mock
    MealPreparationService mockMealPreparationService;
    @BeforeEach
    public void setUp() {
        serviceToTest = new OrderTypeServiceImpl(mockOrderTypeRepository,mockModelMapper, mockUserService,mockOrderService,
                mockMealPreparationService);
    }
    @Test
    public void testGetTableByName() {
        User user = new User();
        user.setFullName("Test Waiter");
        user.setPassword("123456");
        user.setRole(UserRole.WAITER);
        user.setUsername("waiter1");
        OrderType orderType = new OrderType();
        orderType.setActive(true);
        orderType.setName("t1");
        orderType.setWaiter(user);
        Mockito.when(mockOrderTypeRepository.findByName("t1")).thenReturn(java.util.Optional.of(orderType));
        Assertions.assertEquals("t1", serviceToTest.getTableByName("t1").getName());
    }
    @Test
    public void testCheckWaiterForOnlineOrders() {
        User user = new User();
        user.setFullName("Test Waiter");
        user.setPassword("123456");
        user.setRole(UserRole.WAITER);
        user.setUsername("waiter1");
        user.setId(1L);
        OrderType orderType = new OrderType();
        orderType.setActive(true);
        orderType.setName("online");
        orderType.setWaiter(user);
        Mockito.when(mockOrderTypeRepository.findByName("online")).thenReturn(java.util.Optional.of(orderType));
        Assertions.assertEquals(true, serviceToTest.checkWaiterForOnline(1L));
    }
}
