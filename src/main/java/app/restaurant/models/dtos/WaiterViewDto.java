package app.restaurant.models.dtos;

public class WaiterViewDto {
    private String username;
    private String fullName;

    public WaiterViewDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
