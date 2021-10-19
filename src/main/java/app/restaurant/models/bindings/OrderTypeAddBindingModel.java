package app.restaurant.models.bindings;

import app.restaurant.models.entities.User;

import javax.validation.constraints.NotBlank;

public class OrderTypeAddBindingModel {
    private String name;
    private boolean isActive;
    private String waiterName;

    public OrderTypeAddBindingModel() {
    }

    @NotBlank(message = "Meal name cannot be empty")
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

    public String getWaiterName() {
        return waiterName;
    }

    public void setWaiterName(String waiterName) {
        this.waiterName = waiterName;
    }
}
