package app.restaurant.models.bindings;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WaiterAddOrderBindingModelTest {
    private WaiterAddOrderBindingModel toTest;
    @BeforeEach
    public void setUp() {
        toTest = new WaiterAddOrderBindingModel();
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
    public void testGetSetWaiterId() {
        toTest.setWaiterId(1L);
        Assertions.assertEquals(1L, toTest.getWaiterId());
    }
    @Test
    public void testGetSetTableId() {
        toTest.setTableId(1L);
        Assertions.assertEquals(1L, toTest.getTableId());
    }
}
