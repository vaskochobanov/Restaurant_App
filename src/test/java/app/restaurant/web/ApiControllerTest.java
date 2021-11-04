package app.restaurant.web;

import app.restaurant.models.entities.Meal;
import app.restaurant.models.entities.User;
import app.restaurant.models.entities.enums.MealType;
import app.restaurant.models.entities.enums.UserRole;
import app.restaurant.repositories.MealRepository;
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
public class ApiControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private MealRepository mealRepository;
    @Autowired
    private UserRepository userRepository;
    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"}, password = "123456")
    public void shouldReturnValidStatusViewNameAndModelGetAdminAPIMeals() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/meals")).andExpect(status().isOk())
                .andExpect(jsonPath("[0].name").value("Fruit Salad"));
    }
    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"}, password = "123456")
    public void shouldReturnValidStatusViewNameAndModelGetAdminAPIIngredients() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/ingredients")).andExpect(status().isOk())
                .andExpect(jsonPath("[0].name").value("cucumbers"));
    }
    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"}, password = "123456")
    public void shouldReturnValidStatusViewNameAndModelGetAdminAPIUsers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/users")).andExpect(status().isOk())
                .andExpect(jsonPath("[0].username").value("admin"));
    }
    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"}, password = "123456")
    public void shouldReturnValidStatusViewNameAndModelGetAdminAPITables() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/tables")).andExpect(status().isOk())
                .andExpect(jsonPath("[0].name").value("online"));
    }
    @Test
    @WithMockUser(username = "waiter1", roles = {"WAITER"}, password = "123456")
    public void shouldReturnValidStatusViewNameAndModelGetWaiterAPITables() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/waiter-home/3")).andExpect(status().isOk())
                .andExpect(jsonPath("[0].name").value("t1"));
    }
    @Test
    @WithMockUser(username = "waiter1", roles = {"WAITER"}, password = "123456")
    public void shouldReturnValidStatusViewNameAndModelGetWaiterAPIMenu() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/waiter-menu")).andExpect(status().isOk())
                .andExpect(jsonPath("[0].name").value("Beer"));
    }
    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"}, password = "123456")
    public void shouldReturnValidStatusViewNameAndModelGetAdminAPIIngredientsNames() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/ingredients-names")).andExpect(status().isOk())
                .andExpect(jsonPath("[1]").value("beer"));
    }
}
