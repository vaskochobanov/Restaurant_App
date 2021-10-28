package app.restaurant.repositories;

import app.restaurant.models.entities.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {
    Optional<Meal> findByName(String name);
    @Query("select m from Meal m order by m.active desc, m.type")
    List<Meal> findAllMealsSorted();
    @Query("select m from Meal m where m.active = true")
    List<Meal> findAllActiveMeals();
}
