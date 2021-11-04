package app.restaurant.web;

import app.restaurant.models.entities.User;
import app.restaurant.models.entities.enums.UserRole;
import app.restaurant.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class OrderTypeControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;
    @BeforeEach
    public void setUp() {
        this.init();
    }
    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"}, password = "123456")
    public void shouldReturnValidStatusViewNameAndModelGetAdminAddTable() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/tables/add")).andExpect(status().isOk())
                .andExpect(view().name("admin-add-order-type")).andExpect(model().attributeExists("waiters"))
                .andExpect(model().attributeExists("orderTypeAddBindingModel"))
                .andExpect(model().attributeExists("orderTypeExists"));
    }
    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"}, password = "123456")
    public void shouldReturnValidStatusViewNameAndModelGetAdminEditTables() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/tables/edit")).andExpect(status().isOk())
                .andExpect(view().name("admin-edit-tables")).andExpect(model().attributeExists("tables"));
    }
    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"}, password = "123456")
    public void shouldReturnValidStatusViewNameAndModelGetAdminDeleteTable() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/tables/delete/1")).andExpect(status().is(302))
                .andExpect(view().name("redirect:/tables/edit"));
    }
    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"}, password = "123456")
    public void shouldReturnValidStatusViewNameAndModelGetAdminEditTable() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/tables/single-edit/1")).andExpect(status().isOk())
                .andExpect(view().name("edit-single-table")).andExpect(model().attributeExists("tableToEdit"))
                .andExpect(model().attributeExists("waiters")).andExpect(model().attributeExists("hasErrors"));
    }
    @Test
    @WithMockUser(username = "waiter1", roles = {"WAITER"}, password = "123456")
    public void shouldReturnValidStatusViewNameAndModelGetWaiterEditOrder() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/tables/edit-order/1")).andExpect(status().isOk())
                .andExpect(view().name("edit-order")).andExpect(model().attributeExists("waiterId"))
                .andExpect(model().attributeExists("tableId"));
    }
    @Test
    @WithMockUser(username = "waiter1", roles = {"WAITER"}, password = "123456")
    public void shouldReturnValidStatusViewNameAndModelGetWaiterOnlineOrders() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/tables/online-orders")).andExpect(status().isOk())
                .andExpect(view().name("online-orders"));
    }
    private void init() {
        User user = new User();
        user.setFullName("Test User");
        user.setPassword("123456");
        user.setRole(UserRole.ADMIN);
        user.setUsername("admin");
        user.setId(1L);
        userRepository.save(user);
        User waiter = new User();
        waiter.setFullName("Waiter 1");
        waiter.setPassword("123456");
        waiter.setRole(UserRole.WAITER);
        waiter.setUsername("waiter1");
        waiter.setId(3L);
        userRepository.save(waiter);
    }
}
