package app.restaurant.repositories;

import app.restaurant.models.entities.MealPreparation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealPreparationRepository extends JpaRepository<MealPreparation, Long> {
}
