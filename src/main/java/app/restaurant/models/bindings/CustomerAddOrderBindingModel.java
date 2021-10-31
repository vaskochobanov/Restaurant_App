package app.restaurant.models.bindings;

import com.google.gson.annotations.Expose;

public class CustomerAddOrderBindingModel {
    @Expose
    private Long mealId;
    @Expose
    private Integer quantity;
    @Expose
    private Long customerId;

    public CustomerAddOrderBindingModel() {
    }

    public Long getMealId() {
        return mealId;
    }

    public void setMealId(Long mealId) {
        this.mealId = mealId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
