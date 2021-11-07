package app.restaurant.repositories;

import app.restaurant.models.entities.NeedToBuy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NeedToBuyRepository extends JpaRepository<NeedToBuy, Long> {
    @Query("select distinct(n.ingredientName) from NeedToBuy n")
    List<String> findAllUniqueIngredients();
    void deleteNeedToBuyByIngredientName(String name);
}
