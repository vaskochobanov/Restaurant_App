package app.restaurant.services.impl;

import app.restaurant.models.entities.Order;
import app.restaurant.models.entities.OrderType;
import app.restaurant.models.entities.User;
import app.restaurant.models.entities.enums.UserRole;
import app.restaurant.repositories.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class OrderServiceImplTest {
    private OrderServiceImpl serviceToTest;
    @Mock
    OrderRepository mockOrderRepository;
    @BeforeEach
    public void setUp() {
        serviceToTest = new OrderServiceImpl(mockOrderRepository);
    }
    @Test
    public void testSaveOrder() {
        Order order = new Order();
        order.setOpen(true);
        long repoCount = mockOrderRepository.count();
        serviceToTest.saveOrder(order);
        Mockito.when(mockOrderRepository.count()).thenReturn(1L);
        Assertions.assertEquals(repoCount + 1, mockOrderRepository.count());
    }
    @Test
    public void testDeleteOrder() {
        Order order = new Order();
        order.setOpen(true);
        mockOrderRepository.save(order);
        serviceToTest.deleteOrderById(1L);
        Assertions.assertEquals(0, mockOrderRepository.count());
    }
    @Test
    public void testGetOrderIdByCustomerId() {
        Order order = new Order();
        order.setOpen(true);
        mockOrderRepository.save(order);
        Mockito.when(mockOrderRepository.findOrderByCustomerId(1L)).thenReturn(Optional.of(1L));
        Assertions.assertEquals(1L, serviceToTest.getOrderIdByCustomerId(1L));
    }
    @Test
    public void testGetOrderById() {
        Order order = new Order();
        order.setOpen(true);
        Mockito.when(mockOrderRepository.findById(1L)).thenReturn(java.util.Optional.of(order));
        Assertions.assertTrue(serviceToTest.getOrderById(1L).isOpen());
    }
}
