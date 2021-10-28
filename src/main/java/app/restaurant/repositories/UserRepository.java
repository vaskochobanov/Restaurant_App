package app.restaurant.repositories;

import app.restaurant.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    @Query("select u from User u where u.role = 'WAITER'")
    List<User> findAllWaiters();
    @Query("select u.id from User u where u.username = ?1")
    Long findIdByUsername(String name);
}
