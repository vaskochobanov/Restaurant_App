package app.restaurant.repositories;

import app.restaurant.models.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("select o from Order o where o.open = true and o.orderType.id = ?1")
    Optional<Order> findOpenOrderByTableId(Long tableId);
    @Query("select o from Order o where o.open = true and o.orderType.name = ?1")
    Optional<Order> findOpenOrderByTableName(String tableName);
    @Query("select o.id from Order o where o.onlineUser.id = ?1 and o.open = true ")
    Optional<Long> findOrderByCustomerId(Long customerId);
    @Query("select o from Order o where o.open = true and o.onlineUser is not null")
    List<Order> findActiveOnlineOrders();
}
