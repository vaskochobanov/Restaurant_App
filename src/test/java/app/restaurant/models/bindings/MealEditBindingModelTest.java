package app.restaurant.models.bindings;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MealEditBindingModelTest {
    private MealEditBindingModel toTest;
    @BeforeEach
    public void setUp() {
        toTest = new MealEditBindingModel();
    }
    @Test
    public void testGetSetId() {
        toTest.setId(1L);
        Assertions.assertEquals(1L, toTest.getId());
    }
    @Test
    public void testGetSetPrice() {
        toTest.setPrice(12.49);
        Assertions.assertEquals(12.49, toTest.getPrice());
    }
    @Test
    public void testGetSetIngredients() {
        toTest.setIngredients("cucumbers-1,tomatoes-1");
        Assertions.assertEquals("cucumbers-1,tomatoes-1", toTest.getIngredients());
    }
    @Test
    public void testGetSetPromoted() {
        toTest.setPromoted(true);
        Assertions.assertTrue(toTest.isPromoted());
    }
    @Test
    public void testGetSetActive() {
        toTest.setActive(true);
        Assertions.assertTrue(toTest.isActive());
    }
}
