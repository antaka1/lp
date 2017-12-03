package ro.lsacbucuresti.lanpartyquiz.dto;

/**
 * Created by cristi on 06 - October - 2017
 */

public class PasswordResetDTO {

    private String password;

    private String token;

    public PasswordResetDTO() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
