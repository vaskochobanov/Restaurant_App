package app.restaurant.repositories;

import app.restaurant.models.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    Optional<Ingredient> findByName(String name);
    @Query("select i from Ingredient i order by i.bestBefore")
    List<Ingredient> findAllSortedByExpiryDate();
    @Query("select distinct(i.name) from Ingredient i")
    List<String> findAllUniqueIngredientNames();
}
