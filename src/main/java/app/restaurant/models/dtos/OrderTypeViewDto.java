package app.restaurant.models.dtos;

import app.restaurant.models.entities.User;

public class OrderTypeViewDto {
    private Long id;
    private String name;
    private boolean isActive;
    private UserViewDto waiter;

    public OrderTypeViewDto() {
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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public UserViewDto getWaiter() {
        return waiter;
    }

    public void setWaiter(UserViewDto waiter) {
        this.waiter = waiter;
    }
}
