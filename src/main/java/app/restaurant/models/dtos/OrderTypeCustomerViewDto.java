package app.restaurant.models.dtos;

public class OrderTypeCustomerViewDto {
    private String mealName;
    private boolean isPrepared;
    private Double totalSum;
    private boolean notEnoughIngredients;

    public OrderTypeCustomerViewDto() {
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public boolean isPrepared() {
        return isPrepared;
    }

    public void setPrepared(boolean prepared) {
        isPrepared = prepared;
    }

    public Double getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(Double totalSum) {
        this.totalSum = totalSum;
    }

    public boolean isNotEnoughIngredients() {
        return notEnoughIngredients;
    }

    public void setNotEnoughIngredients(boolean notEnoughIngredients) {
        this.notEnoughIngredients = notEnoughIngredients;
    }
}
