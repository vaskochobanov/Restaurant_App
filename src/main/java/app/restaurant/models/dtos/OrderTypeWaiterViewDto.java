package app.restaurant.models.dtos;

import java.util.List;

public class OrderTypeWaiterViewDto {
    private Long id;
    private String name;
    private boolean isFree;
    private Double sum;
    List<MealPreparationWaiterViewDto> listMeals;

    public OrderTypeWaiterViewDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public List<MealPreparationWaiterViewDto> getListMeals() {
        return listMeals;
    }

    public void setListMeals(List<MealPreparationWaiterViewDto> listMeals) {
        this.listMeals = listMeals;
    }
}
