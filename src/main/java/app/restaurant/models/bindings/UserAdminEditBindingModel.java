package app.restaurant.models.bindings;

public class UserAdminEditBindingModel {
    private Long id;
    private String role;

    public UserAdminEditBindingModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
