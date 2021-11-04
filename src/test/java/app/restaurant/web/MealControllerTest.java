package app.restaurant.web;

import app.restaurant.models.entities.User;
import app.restaurant.models.entities.enums.UserRole;
import app.restaurant.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class MealControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;
    @BeforeEach
    public void setUp() {
        this.init();
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
