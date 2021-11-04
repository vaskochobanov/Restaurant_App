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
    @BeforeEach
    public void setUp() {
        this.init();
    }
    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"}, password = "123456")
    public void shouldReturnValidStatusViewNameAndModelGetAdminAPIMeals() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/meals")).andExpect(status().isOk())
                .andExpect(jsonPath("[0].name").value("testmeal"));
    }
    private void init() {
        User user = new User();
        user.setFullName("Test User");
        user.setPassword("123456");
        user.setRole(UserRole.ADMIN);
        user.setUsername("admin");
        user.setId(1L);
        userRepository.save(user);
        Meal meal = new Meal();
        meal.setActive(true);
        meal.setDescription("Test meal description");
        meal.setId(1L);
        meal.setImageUrl("http://testmeal.jpg");
        meal.setIngredients("cucumbers-0.1,tomatoes-0.1");
        meal.setName("testmeal");
        meal.setPrice(4.99);
        meal.setPromoted(false);
        meal.setType(MealType.SALAD);
        mealRepository.save(meal);
    }
}
