package app.restaurant.models.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    private Long id;
    private boolean isOpen;
    private OrderType orderType;
    private List<MealPreparation> mealList;

    public Order() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "is_open", nullable = false)
    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "order_type_id", referencedColumnName = "id")
    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    @OneToMany(mappedBy = "order", targetEntity = MealPreparation.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public List<MealPreparation> getMealList() {
        return mealList;
    }

    public void setMealList(List<MealPreparation> mealList) {
        this.mealList = mealList;
    }
}
