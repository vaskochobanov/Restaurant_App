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

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class AdminControllerTest {
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
    public void shouldReturnValidStatusViewNameAndModelGetAdminAddUsers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/add-user")).andExpect(status().isOk())
                .andExpect(view().name("admin-add-user")).andExpect(model().attributeExists("roles"))
                .andExpect(model().attributeExists("userAdminRegisterBindingModel"))
                .andExpect(model().attributeExists("usernameTaken"));
    }
    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"}, password = "123456")
    public void shouldReturnValidStatusViewNameAndModelGetAdminEditUsers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/edit-user")).andExpect(status().isOk())
                .andExpect(view().name("admin-edit-users")).andExpect(model().attributeExists("users"))
                .andExpect(model().attributeExists("currentUser"));
    }
    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"}, password = "123456")
    public void shouldReturnValidStatusViewNameAndModelGetAdminDeleteUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/user-delete/1")).andExpect(status().is(302))
                .andExpect(view().name("redirect:/admin/edit-user"));
    }
    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"}, password = "123456")
    public void shouldReturnValidStatusViewNameAndModelGetAdminEditUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/admin/user-edit/1")).andExpect(status().isOk())
                .andExpect(view().name("edit-single-user")).andExpect(model().attributeExists("roles"))
                .andExpect(model().attributeExists("userForEdit"))
                .andExpect(model().attributeExists("nullError"));
    }
    private void init() {
        User user = new User();
        user.setFullName("Test User");
        user.setPassword("123456");
        user.setRole(UserRole.ADMIN);
        user.setUsername("admin");
        user.setId(1L);
        userRepository.save(user);
    }
}
