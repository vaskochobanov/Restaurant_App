package app.restaurant.models.dtos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MealPreparationViewDtoTest {
    private MealPreparationViewDto toTest;
    @BeforeEach
    public void setUp() {
        toTest = new MealPreparationViewDto();
    }
    @Test
    public void testGetSetId() {
        toTest.setId(1L);
        Assertions.assertEquals(1L, toTest.getId());
    }
    @Test
    public void testGetSetMealName() {
        toTest.setMealName("testmeal");
        Assertions.assertEquals("testmeal", toTest.getMealName());
    }
    @Test
    public void testGetSetMealIngredients() {
        toTest.setMealIngredients("cucumbers-1,tomatoes-1");
        Assertions.assertEquals("cucumbers-1,tomatoes-1", toTest.getMealIngredients());
    }
    @Test
    public void testGetSetCount() {
        toTest.setCount(12);
        Assertions.assertEquals(12, toTest.getCount());
    }
    @Test
    public void testGetSetPrepared() {
        toTest.setPrepared(true);
        Assertions.assertTrue(toTest.isPrepared());
    }
    @Test
    public void testGetSetNotEnoughIngredients() {
        toTest.setNotEnoughIngredients(true);
        Assertions.assertTrue(toTest.isNotEnoughIngredients());
    }
    @Test
    public void testGetSetOrderId() {
        toTest.setOrderId(1L);
        Assertions.assertEquals(1L, toTest.getOrderId());
    }
}
