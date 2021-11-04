package app.restaurant.web;

import app.restaurant.models.entities.User;
import app.restaurant.models.entities.enums.UserRole;
import app.restaurant.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
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
public class MealControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;
    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"}, password = "123456")
    public void shouldReturnValidStatusViewNameAndModelGetAdminAddMeal() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/meals/add")).andExpect(status().isOk())
                .andExpect(view().name("admin-add-meal")).andExpect(model().attributeExists("types"))
                .andExpect(model().attributeExists("mealAddBindingModel"))
                .andExpect(model().attributeExists("mealExists"));
    }
    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"}, password = "123456")
    public void shouldReturnValidStatusViewNameAndModelGetAdminEditMeal() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/meals/edit")).andExpect(status().isOk())
                .andExpect(view().name("admin-edit-meals")).andExpect(model().attributeExists("meals"));
    }
    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"}, password = "123456")
    public void shouldReturnValidStatusViewNameAndModelGetAdminDeleteUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/meals/delete/1")).andExpect(status().is(302))
                .andExpect(view().name("redirect:/meals/edit"));
    }
    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"}, password = "123456")
    public void shouldReturnValidStatusViewNameAndModelGetAdminEditSingleMeal() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/meals/single-edit/1")).andExpect(status().isOk())
                .andExpect(view().name("edit-single-meal")).andExpect(model().attributeExists("mealForEdit"))
                .andExpect(model().attributeExists("hasErrors")).andExpect(model().attributeExists("priceNotValid"))
                .andExpect(model().attributeExists("ingredientsNotValid"));
    }
    @Test
    @WithMockUser(username = "waiter1", roles = {"WAITER"}, password = "123456")
    public void shouldReturnValidStatusViewNameAndModelGetWaiterMealsMenu() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/meals/meals-menu/2")).andExpect(status().isOk())
                .andExpect(view().name("waiter-menu")).andExpect(model().attributeExists("waiterId"))
                .andExpect(model().attributeExists("tableId"));
    }
}
