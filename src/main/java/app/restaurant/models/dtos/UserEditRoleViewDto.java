package app.restaurant.models.dtos;

public class UserEditRoleViewDto {
    private Long id;
    private String role;

    public UserEditRoleViewDto() {
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
