package app.restaurant.models.bindings;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserAdminRegisterBindingModelTest {
    private UserAdminRegisterBindingModel toTest;
    @BeforeEach
    public void setUp() {
        toTest = new UserAdminRegisterBindingModel();
    }
    @Test
    public void testGetSetUsername() {
        toTest.setUsername("testuser");
        Assertions.assertEquals("testuser", toTest.getUsername());
    }
    @Test
    public void testGetSetFullname() {
        toTest.setFullName("test user");
        Assertions.assertEquals("test user", toTest.getFullName());
    }
    @Test
    public void testGetSetRole() {
        toTest.setRole("CUSTOMER");
        Assertions.assertEquals("CUSTOMER", toTest.getRole());
    }
    @Test
    public void testGetSetPassword() {
        toTest.setPassword("123456");
        Assertions.assertEquals("123456", toTest.getPassword());
    }
    @Test
    public void testGetSetConfirmPassword() {
        toTest.setConfirmPassword("123456");
        Assertions.assertEquals("123456", toTest.getConfirmPassword());
    }
}
