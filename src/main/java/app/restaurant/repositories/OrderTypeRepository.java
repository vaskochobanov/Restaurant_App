package app.restaurant.repositories;

import app.restaurant.models.entities.OrderType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderTypeRepository extends JpaRepository<OrderType, Long> {
    Optional<OrderType> findByName(String name);
}
