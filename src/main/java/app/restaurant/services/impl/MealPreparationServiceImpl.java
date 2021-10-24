package app.restaurant.services.impl;

import app.restaurant.models.entities.MealPreparation;
import app.restaurant.repositories.MealPreparationRepository;
import app.restaurant.services.MealPreparationService;
import org.springframework.stereotype.Service;

@Service
public class MealPreparationServiceImpl implements MealPreparationService {
    private final MealPreparationRepository mealPreparationRepository;

    public MealPreparationServiceImpl(MealPreparationRepository mealPreparationRepository) {
        this.mealPreparationRepository = mealPreparationRepository;
    }

    @Override
    public void saveMealPreparation(MealPreparation mealPreparation) {
        mealPreparationRepository.save(mealPreparation);
    }
}
