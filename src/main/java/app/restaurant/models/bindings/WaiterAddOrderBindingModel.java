package app.restaurant.models.bindings;

import com.google.gson.annotations.Expose;

public class WaiterAddOrderBindingModel {
    @Expose
    private Long mealId;
    @Expose
    private Integer quantity;
    @Expose
    private Long waiterId;
    @Expose
    private Long tableId;

    public WaiterAddOrderBindingModel() {
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

    public Long getWaiterId() {
        return waiterId;
    }

    public void setWaiterId(Long waiterId) {
        this.waiterId = waiterId;
    }

    public Long getTableId() {
        return tableId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }
}
