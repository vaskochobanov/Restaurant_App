package app.restaurant.services.impl;

import app.restaurant.models.entities.Order;
import app.restaurant.repositories.MealPreparationRepository;
import app.restaurant.services.IngredientService;
import app.restaurant.services.MealPreparationService;
import app.restaurant.services.MealService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

@ExtendWith(MockitoExtension.class)
public class MealPreparationServiceImplTest {
    private MealPreparationServiceImpl serviceToTest;
    @Mock
    MealPreparationRepository mockMealPreparationRepository;
    @Mock
    MealService mockMealService;
    @Mock
    ModelMapper mockModelMapper;
    @Mock
    IngredientService mockIngredientService;
    @BeforeEach
    public void setUp() {
        serviceToTest = new MealPreparationServiceImpl(mockMealPreparationRepository, mockMealService, mockModelMapper,
                mockIngredientService);
    }
    @Test
    public void testInitDrinkMealPreparations() {
        Order order = new Order();
        order.setOpen(true);
        Mockito.when(mockMealPreparationRepository.count()).thenReturn(3L);
        serviceToTest.initDrinkMealPreparations(order);
        Assertions.assertEquals(3, mockMealPreparationRepository.count());
    }
    @Test
    public void testInitDrinkSaladPreparations() {
        Order order = new Order();
        order.setOpen(true);
        Mockito.when(mockMealPreparationRepository.count()).thenReturn(2L);
        serviceToTest.initSaladMealPreparations(order);
        Assertions.assertEquals(2, mockMealPreparationRepository.count());
    }
    @Test
    public void testInitDrinkMainDishPreparations() {
        Order order = new Order();
        order.setOpen(true);
        Mockito.when(mockMealPreparationRepository.count()).thenReturn(2L);
        serviceToTest.initMainDishesMealPreparations(order);
        Assertions.assertEquals(2, mockMealPreparationRepository.count());
    }
    @Test
    public void testInitDrinkDessertPreparations() {
        Order order = new Order();
        order.setOpen(true);
        Mockito.when(mockMealPreparationRepository.count()).thenReturn(2L);
        serviceToTest.initDessertsMealPreparations(order);
        Assertions.assertEquals(2, mockMealPreparationRepository.count());
    }
    @Test
    public void testGetSumOfOrderId() {
        Mockito.when(mockMealPreparationRepository.findSumOfMealsInOrder(1L)).thenReturn(8.49);
        Assertions.assertEquals(8.49, serviceToTest.getSumOfOrderId(1L));
    }
}
