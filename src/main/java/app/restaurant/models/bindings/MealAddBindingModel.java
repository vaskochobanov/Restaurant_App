package app.restaurant.models.bindings;

import app.restaurant.models.entities.enums.MealType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class MealAddBindingModel {
    private String name;
    private String type;
    private Double price;
    private String description;
    private String imageUrl;
    private boolean isPromoted;
    private boolean isActive;
    private String ingredients;

    public MealAddBindingModel() {
    }

    @NotBlank(message = "Name cannot be empty")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull(message = "You must select category for the meal")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @NotNull(message = "Price cannot be empty")
    @Positive(message = "Price must be positive number")
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @NotBlank(message = "Description cannot be empty")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NotBlank(message = "Image URL cannot be empty")
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isPromoted() {
        return isPromoted;
    }

    public void setPromoted(boolean promoted) {
        isPromoted = promoted;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @NotBlank(message = "You must input at least one ingredient")
    @Size(min = 5, message = "Ingredient format is not valid")
    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }
}
