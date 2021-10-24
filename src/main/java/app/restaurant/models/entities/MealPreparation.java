package app.restaurant.models.entities;

import javax.persistence.*;

@Entity
@Table(name = "meal_preparations")
public class MealPreparation {
    private Long id;
    private Meal meal;
    private Integer count;
    private boolean isPrepared;
    private boolean notEnoughIngredients;

    public MealPreparation() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne()
    @JoinColumn(name = "meal_id", referencedColumnName = "id")
    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    @Column(name = "count", nullable = false)
    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Column(name = "is_prepared", nullable = false)
    public boolean isPrepared() {
        return isPrepared;
    }

    public void setPrepared(boolean prepared) {
        isPrepared = prepared;
    }

    @Column(name = "not_enough_ingredients", nullable = false)
    public boolean isNotEnoughIngredients() {
        return notEnoughIngredients;
    }

    public void setNotEnoughIngredients(boolean notEnoughIngredients) {
        this.notEnoughIngredients = notEnoughIngredients;
    }
}
