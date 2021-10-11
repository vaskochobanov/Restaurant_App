package app.restaurant.models.entities;

import app.restaurant.models.entities.enums.UserRole;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    private Long id;
    private String username;
    private String fullName;
    private String password;
    private UserRole role;
    private List<Order> activeOrders;
    private List<Order> historyOrders;

    public User() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "username", unique = true, nullable = false)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "full_name")
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Enumerated(EnumType.STRING)
    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    @ManyToMany
    @JoinTable(name = "users_orders_active", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"))
    public List<Order> getActiveOrders() {
        return activeOrders;
    }

    public void setActiveOrders(List<Order> activeOrders) {
        this.activeOrders = activeOrders;
    }

    @ManyToMany
    @JoinTable(name = "users_orders_history", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"))
    public List<Order> getHistoryOrders() {
        return historyOrders;
    }

    public void setHistoryOrders(List<Order> historyOrders) {
        this.historyOrders = historyOrders;
    }
}
