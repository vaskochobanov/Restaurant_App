package app.restaurant.models.bindings;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IngredientAddBindingModelTest {
    private IngredientAddBindingModel toTest;
    @BeforeEach
    public void setUp() {
        toTest = new IngredientAddBindingModel();
    }
    @Test
    public void testGetSetImageURL() {
        toTest.setImageUrl("Test");
        Assertions.assertEquals("Test", toTest.getImageUrl());
    }
    @Test
    public void testGetSetName() {
        toTest.setName("Test");
        Assertions.assertEquals("Test", toTest.getName());
    }
    @Test
    public void testGetSetQuantity() {
        toTest.setQuantity(12);
        Assertions.assertEquals(12, toTest.getQuantity());
    }
}
