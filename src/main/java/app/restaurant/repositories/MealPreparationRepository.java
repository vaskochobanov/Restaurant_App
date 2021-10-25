package app.restaurant.repositories;

import app.restaurant.models.entities.MealPreparation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MealPreparationRepository extends JpaRepository<MealPreparation, Long> {
    @Query("select mp from MealPreparation mp where mp.order.open = true ")
    List<MealPreparation> findMealsFromOpenOrders();
}
