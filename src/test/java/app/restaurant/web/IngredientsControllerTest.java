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
public class IngredientsControllerTest {
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
    public void shouldReturnValidStatusViewNameAndModelGetAdminAddIngredient() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/ingredients/add")).andExpect(status().isOk())
                .andExpect(view().name("admin-add-ingredient"))
                .andExpect(model().attributeExists("ingredientAddBindingModel"));
    }
    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"}, password = "123456")
    public void shouldReturnValidStatusViewNameAndModelGetAdminEditStorage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/ingredients/edit-storage")).andExpect(status().isOk())
                .andExpect(view().name("admin-edit-ingredients"))
                .andExpect(model().attributeExists("ingredients"));
    }
    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"}, password = "123456")
    public void shouldReturnValidStatusViewNameAndModelGetAdminDeleteIngredient() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/ingredients/delete/1")).andExpect(status().is(302))
                .andExpect(view().name("redirect:/ingredients/edit-storage"));
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
