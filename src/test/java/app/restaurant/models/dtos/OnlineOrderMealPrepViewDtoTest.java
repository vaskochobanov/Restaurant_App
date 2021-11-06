package app.restaurant.models.dtos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OnlineOrderMealPrepViewDtoTest {
    private OnlineOrderMealPrepViewDto toTest;
    @BeforeEach
    public void setUp() {
        toTest = new OnlineOrderMealPrepViewDto();
    }
    @Test
    public void testGetSetMealName() {
        toTest.setMealName("testmeal");
        Assertions.assertEquals("testmeal", toTest.getMealName());
    }
    @Test
    public void testGetSetPrepared() {
        toTest.setPrepared(true);
        Assertions.assertTrue(toTest.isPrepared());
    }
}
