package ro.lsacbucuresti.lanpartyquiz.dto;

import ro.lsacbucuresti.lanpartyquiz.model.User;

/**
 * Created by cristi on 05 - October - 2017
 */
public class UserDashDTO {

    private Long id;

    private String email;

    private String username;

    private String phone;

    private Integer points;

    public UserDashDTO() {
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

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public static UserDashDTO fromUser(User user, Integer points) {
        UserDashDTO userDTO = new UserDashDTO();
        userDTO.setEmail(user.getEmail());
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setPoints(points);
        userDTO.setPhone(user.getPhone());
        return userDTO;
    }
}
