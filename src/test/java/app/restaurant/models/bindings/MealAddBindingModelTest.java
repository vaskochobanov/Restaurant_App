package app.restaurant.models.bindings;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MealAddBindingModelTest {
    private MealAddBindingModel toTest;
    @BeforeEach
    public void setUp() {
        toTest = new MealAddBindingModel();
    }
    @Test
    public void testGetSetName() {
        toTest.setName("testmeal");
        Assertions.assertEquals("testmeal", toTest.getName());
    }
    @Test
    public void testGetSetType() {
        toTest.setType("SALAD");
        Assertions.assertEquals("SALAD", toTest.getType());
    }
    @Test
    public void testGetSetPrice() {
        toTest.setPrice(12.49);
        Assertions.assertEquals(12.49, toTest.getPrice());
    }
    @Test
    public void testGetSetDescription() {
        toTest.setDescription("Test Desc");
        Assertions.assertEquals("Test Desc", toTest.getDescription());
    }
    @Test
    public void testGetSetImageURL() {
        toTest.setImageUrl("Test");
        Assertions.assertEquals("Test", toTest.getImageUrl());
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
    @Test
    public void testGetSetIngredients() {
        toTest.setIngredients("Test Desc");
        Assertions.assertEquals("Test Desc", toTest.getIngredients());
    }
}
