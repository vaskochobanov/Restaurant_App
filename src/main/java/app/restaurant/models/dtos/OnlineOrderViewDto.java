package app.restaurant.models.dtos;

import java.util.List;

public class OnlineOrderViewDto {
    private Long id;
    private String customerName;
    private Double totalSum;
    private List<OnlineOrderMealPrepViewDto> listMeals;

    public OnlineOrderViewDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Double getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(Double totalSum) {
        this.totalSum = totalSum;
    }

    public List<OnlineOrderMealPrepViewDto> getListMeals() {
        return listMeals;
    }

    public void setListMeals(List<OnlineOrderMealPrepViewDto> listMeals) {
        this.listMeals = listMeals;
    }
}
