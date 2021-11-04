package app.restaurant.web;

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
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Test
    public void shouldReturnValidStatusViewNameAndModelGetRegisterUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/register")).andExpect(status().isOk())
                .andExpect(view().name("register"))
                .andExpect(model().attributeExists("userRegisterBindingModel"))
                .andExpect(model().attributeExists("usernameTaken"));
    }
    @Test
    public void shouldReturnValidStatusViewNameAndModelGetLoginUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/login")).andExpect(status().isOk())
                .andExpect(view().name("login"));
    }
}
