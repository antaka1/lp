package ro.lsacbucuresti.lanpartyquiz.dto;

import ro.lsacbucuresti.lanpartyquiz.model.User;

/**
 * Created by cristi on 05 - October - 2017
 */
public class UserDTO {

    private Long id;

    private String email;

    private String username;


    public UserDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public static UserDTO fromUser(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(user.getEmail());
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        return userDTO;
    }
}
