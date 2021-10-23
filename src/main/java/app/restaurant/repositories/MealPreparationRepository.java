package app.restaurant.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealPreparationRepository extends JpaRepository<MealPreparationRepository, Long> {
}
