package app.restaurant.models.bindings;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderTypeEditBindingModelTest {
    private OrderTypeEditBindingModel toTest;
    @BeforeEach
    public void setUp() {
        toTest = new OrderTypeEditBindingModel();
    }
    @Test
    public void testGetSetId() {
        toTest.setId(1L);
        Assertions.assertEquals(1L, toTest.getId());
    }
    @Test
    public void testGetSetWaiterName() {
        toTest.setWaiterName("Spiro");
        Assertions.assertEquals("Spiro", toTest.getWaiterName());
    }
    @Test
    public void testGetSetActive() {
        toTest.setActive(true);
        Assertions.assertTrue(toTest.isActive());
    }
}
