package app.restaurant.models.dtos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class WaiterMealsInOrderTest {
    private WaiterMealsInOrder toTest;
    @BeforeEach
    public void setUp() {
        toTest = new WaiterMealsInOrder();
    }
    @Test
    public void testGetSetMealName() {
        toTest.setMealName("testmeal");
        Assertions.assertEquals("testmeal", toTest.getMealName());
    }
    @Test
    public void testGetSetCount() {
        toTest.setCount(5);
        Assertions.assertEquals(5, toTest.getCount());
    }
}
