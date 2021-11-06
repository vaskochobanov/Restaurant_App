package app.restaurant.models.bindings;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CustomerAddOrderBindingModelTest {
    private CustomerAddOrderBindingModel toTest;
    @BeforeEach
    public void setUp() {
        toTest = new CustomerAddOrderBindingModel();
    }
    @Test
    public void testGetSetMealId() {
        toTest.setMealId(1L);
        Assertions.assertEquals(1L, toTest.getMealId());
    }
    @Test
    public void testGetSetQuantity() {
        toTest.setQuantity(12);
        Assertions.assertEquals(12, toTest.getQuantity());
    }
    @Test
    public void testGetSetCustomerId() {
        toTest.setCustomerId(1L);
        Assertions.assertEquals(1L, toTest.getCustomerId());
    }
}
