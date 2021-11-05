package app.restaurant.services.impl;

import app.restaurant.models.entities.Meal;
import app.restaurant.models.entities.enums.MealType;
import app.restaurant.repositories.MealRepository;
import app.restaurant.services.IngredientService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

@ExtendWith(MockitoExtension.class)
public class MealServiceImplTest {
    private MealServiceImpl serviceToTest;
    @Mock
    MealRepository mockMealRepository;
    @Mock
    ModelMapper mockModelMapper;
    @Mock
    IngredientService mockIngredientService;
    @BeforeEach
    public void setUp() {
        serviceToTest = new MealServiceImpl(mockMealRepository, mockModelMapper, mockIngredientService);
    }
    @Test
    public void testGetMealByName() {
        Meal meal = new Meal();
        meal.setActive(true);
        meal.setDescription("Test Meal");
        meal.setImageUrl("http://testUrl");
        meal.setIngredients("cucumbers-1,tomatoes-1");
        meal.setName("testmeal");
        meal.setPrice(3.49);
        meal.setPromoted(false);
        meal.setType(MealType.SALAD);
        Mockito.when(mockMealRepository.findByName("testmeal")).thenReturn(java.util.Optional.of(meal));
        Assertions.assertEquals("testmeal", serviceToTest.getMealByName("testmeal").getName());
    }
}
