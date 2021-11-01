package app.restaurant.models.dtos;

public class OnlineOrderMealPrepViewDto {
    private String mealName;
    private boolean isPrepared;

    public OnlineOrderMealPrepViewDto() {
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
}
