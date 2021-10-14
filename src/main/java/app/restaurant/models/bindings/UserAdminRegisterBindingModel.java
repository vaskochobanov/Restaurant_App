package app.restaurant.models.bindings;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserAdminRegisterBindingModel {
    private String username;
    private String fullName;
    private String role;
    private String password;
    private String confirmPassword;

    public UserAdminRegisterBindingModel() {
    }

    @NotBlank(message = "Username cannot be empty")
    @Size(min = 5, message = "Username must be at least 5 characters")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotBlank(message = "Name cannot be empty")
    @Size(min = 5, message = "Name must be at least 5 characters")
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @NotBlank(message = "You must select role")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 5, message = "Password must be at least 5 characters")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 5, message = "Password must be at least 5 characters")
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
