package app.restaurant.models.bindings;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserAdminEditBindingModelTest {
    private UserAdminEditBindingModel toTest;
    @BeforeEach
    public void setUp() {
        toTest = new UserAdminEditBindingModel();
    }
    @Test
    public void testGetSetId() {
        toTest.setId(1L);
        Assertions.assertEquals(1L, toTest.getId());
    }
    @Test
    public void testGetSetRole() {
        toTest.setRole("CUSTOMER");
        Assertions.assertEquals("CUSTOMER", toTest.getRole());
    }
}
