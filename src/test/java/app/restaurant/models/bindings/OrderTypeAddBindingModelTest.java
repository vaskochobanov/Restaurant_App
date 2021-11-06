package app.restaurant.models.bindings;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

public class OrderTypeAddBindingModelTest {
    private OrderTypeAddBindingModel toTest;
    @BeforeEach
    public void setUp() {
        toTest = new OrderTypeAddBindingModel();
    }
    @Test
    public void testGetSetName() {
        toTest.setName("t1");
        Assertions.assertEquals("t1", toTest.getName());
    }
    @Test
    public void testGetSetActive() {
        toTest.setActive(true);
        Assertions.assertTrue(toTest.isActive());
    }
    @Test
    public void testGetSetWaiterName() {
        toTest.setWaiterName("Spiro");
        Assertions.assertEquals("Spiro", toTest.getWaiterName());
    }
}
