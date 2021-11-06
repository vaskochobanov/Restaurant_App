package app.restaurant.models.entities;

import app.restaurant.models.entities.enums.UserRole;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class OrderTest {
    private Order orderToTest;
    @BeforeEach
    public void setUp() {
        orderToTest = new Order();
    }
    @Test
    public void testGetId() {
        orderToTest.setId(1L);
        Assertions.assertEquals(1L, orderToTest.getId());
    }
    @Test
    public void testSetGetOrderType() {
        OrderType orderType = new OrderType();
        orderType.setName("t1");
        orderType.setActive(true);
        orderToTest.setOrderType(orderType);
        Assertions.assertEquals("t1", orderToTest.getOrderType().getName());
    }
    @Test
    public void testGetSetOnlineUser() {
        User onlineUser = new User();
        onlineUser.setFullName("Online User");
        onlineUser.setPassword("123456");
        onlineUser.setRole(UserRole.CUSTOMER);
        onlineUser.setUsername("customer1");
        orderToTest.setOnlineUser(onlineUser);
        Assertions.assertEquals("customer1", orderToTest.getOnlineUser().getUsername());
    }
}
