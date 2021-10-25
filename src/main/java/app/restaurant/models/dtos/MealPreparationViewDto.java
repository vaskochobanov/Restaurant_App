package app.restaurant.models.dtos;

import app.restaurant.models.entities.Meal;
import app.restaurant.models.entities.Order;

public class MealPreparationViewDto {
    private Long id;
    private String mealName;
    private String mealIngredients;
    private Integer count;
    private boolean isPrepared;
    private boolean notEnoughIngredients;
    private Long orderId;

    public MealPreparationViewDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getMealIngredients() {
        return mealIngredients;
    }

    public void setMealIngredients(String mealIngredients) {
        this.mealIngredients = mealIngredients;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public boolean isPrepared() {
        return isPrepared;
    }

    public void setPrepared(boolean prepared) {
        isPrepared = prepared;
    }

    public boolean isNotEnoughIngredients() {
        return notEnoughIngredients;
    }

    public void setNotEnoughIngredients(boolean notEnoughIngredients) {
        this.notEnoughIngredients = notEnoughIngredients;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
