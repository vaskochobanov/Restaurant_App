package app.restaurant.models.dtos;

public class WaiterMealsInOrder {
    private String mealName;
    private Integer count;

    public WaiterMealsInOrder() {
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
