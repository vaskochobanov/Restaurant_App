package app.restaurant.models.entities;

import app.restaurant.models.entities.enums.MealType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MealPreparationTest {
    private MealPreparation mealPreparationToTest;
    @BeforeEach
    public void setUp() {
        mealPreparationToTest = new MealPreparation();
    }
    @Test
    public void testSetId() {
        mealPreparationToTest.setId(1L);
        Assertions.assertEquals(1L, mealPreparationToTest.getId());
    }
    @Test
    public void testGetMeal() {
        Meal meal = new Meal();
        meal.setActive(true);
        meal.setDescription("Test Meal");
        meal.setImageUrl("http://some-image.jpg");
        meal.setIngredients("cucumbers-1,tomatoes-1");
        meal.setName("testmeal");
        meal.setPrice(3.49);
        meal.setPromoted(false);
        meal.setType(MealType.SALAD);
        mealPreparationToTest.setMeal(meal);
        Assertions.assertEquals("testmeal", mealPreparationToTest.getMeal().getName());
    }
    @Test
    public void testGetCount() {
        mealPreparationToTest.setCount(3);
        Assertions.assertEquals(3, mealPreparationToTest.getCount());
    }
    @Test
    public void testSetPrepared() {
        mealPreparationToTest.setPrepared(true);
        Assertions.assertTrue(mealPreparationToTest.isPrepared());
    }
    @Test
    public void testGetNotEnoughIngredients() {
        mealPreparationToTest.setNotEnoughIngredients(true);
        Assertions.assertTrue(mealPreparationToTest.isNotEnoughIngredients());
    }
    @Test
    public void testGetOrder() {
        Order order = new Order();
        order.setOpen(true);
        mealPreparationToTest.setOrder(order);
        Assertions.assertTrue(mealPreparationToTest.getOrder().isOpen());
    }
}
