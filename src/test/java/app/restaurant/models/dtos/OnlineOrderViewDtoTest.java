package app.restaurant.models.dtos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OnlineOrderViewDtoTest {
    private OnlineOrderViewDto toTest;
    @BeforeEach
    public void setUp() {
        toTest = new OnlineOrderViewDto();
    }
    @Test
    public void testGetSetId() {
        toTest.setId(1L);
        Assertions.assertEquals(1L, toTest.getId());
    }
    @Test
    public void testGetCustomerName() {
        toTest.setCustomerName("gosho");
        Assertions.assertEquals("gosho", toTest.getCustomerName());
    }
    @Test
    public void testGetTotalSum() {
        toTest.setTotalSum(18.49);
        Assertions.assertEquals(18.49, toTest.getTotalSum());
    }
}
