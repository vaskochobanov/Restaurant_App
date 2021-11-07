package app.restaurant.models.entities;

import javax.persistence.*;

@Entity
@Table(name = "need_to_buy")
public class NeedToBuy {
    private Long id;
    private String ingredientName;

    public NeedToBuy() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "ingredient_name", nullable = false)
    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }
}
