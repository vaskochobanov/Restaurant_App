package app.restaurant.web;

import app.restaurant.models.entities.User;
import app.restaurant.models.entities.enums.UserRole;
import app.restaurant.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class HomeControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void shouldReturnValidStatusViewNameAndModelIndex() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/")).andExpect(status().isOk())
                .andExpect(view().name("index")).andExpect(model().attributeExists("promSalad"))
                .andExpect(model().attributeExists("promMainDish")).andExpect(model().attributeExists("promDessert"));
    }
    @Test
    @WithMockUser(username = "customer1", roles = {"CUSTOMER"}, password = "123456")
    public void shouldReturnValidStatusViewNameAndModelGetCustomerHomeOnlineOrder() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/home-customer-order")).andExpect(status().isOk())
                .andExpect(view().name("home-customer-order")).andExpect(model().attributeExists("hasOrder"))
                .andExpect(model().attributeExists("customerId"))
                .andExpect(model().attributeExists("tableId"));
    }
    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"}, password = "123456")
    public void shouldReturnValidStatusViewNameAndModelGetAdminHome() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/home")).andExpect(status().isOk())
                .andExpect(view().name("home-admin"));
    }
    private void init() {
        User user = new User();
        user.setFullName("Customer 1");
        user.setPassword("123456");
        user.setRole(UserRole.CUSTOMER);
        user.setUsername("customer1");
        user.setId(1L);
        User admin = new User();
        admin.setFullName("Admin 1");
        admin.setPassword("123456");
        admin.setRole(UserRole.ADMIN);
        admin.setUsername("admin");
        admin.setId(2L);
        userRepository.save(admin);
    }
}
