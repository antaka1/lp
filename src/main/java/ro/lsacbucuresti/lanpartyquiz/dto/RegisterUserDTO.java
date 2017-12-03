package ro.lsacbucuresti.lanpartyquiz.dto;

/**
 * Created by cristi on 05 - October - 2017
 */
public class RegisterUserDTO {

    private String username;
    private String email;
    private String phone;
    private String password;
    private boolean facebook;

    public RegisterUserDTO() {
    }

    public RegisterUserDTO(String username, String email, String phone, String password) {
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isFacebook() {
        return facebook;
    }

    public void setFacebook(boolean facebook) {
        this.facebook = facebook;
    }
}
