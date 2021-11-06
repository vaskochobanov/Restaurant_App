package app.restaurant.models.dtos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderTypeCustomerViewDtoTest {
    private OrderTypeCustomerViewDto toTest;
    @BeforeEach
    public void setUp() {
        toTest = new OrderTypeCustomerViewDto();
    }
    @Test
    public void testGetSetMealName() {
        toTest.setMealName("testmeal");
        Assertions.assertEquals("testmeal", toTest.getMealName());
    }
    @Test
    public void testGetSetIsPrepared() {
        toTest.setPrepared(true);
        Assertions.assertTrue(toTest.isPrepared());
    }
    @Test
    public void testGetSetTotalSum() {
        toTest.setTotalSum(18.49);
        Assertions.assertEquals(18.49, toTest.getTotalSum());
    }
}
