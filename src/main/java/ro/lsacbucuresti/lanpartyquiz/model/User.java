package ro.lsacbucuresti.lanpartyquiz.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by cristi on 05 - October - 2017
 */
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true,nullable = false)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = true)
    private String phone;

    @Column(nullable = false)
    private String password;

    private String activationToken;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(nullable = false)
    private Character active;

    private String resetPasswordToken;

    private Date resertSendDate;

    private Character facebook;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getActivationToken() {
        return activationToken;
    }

    public void setActivationToken(String activationToken) {
        this.activationToken = activationToken;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public boolean isActive() {
        return active.equals('Y');
    }

    public void setActive(Character active) {
        this.active = active;
    }

    public String getResetPasswordToken() {
        return resetPasswordToken;
    }

    public void setResetPasswordToken(String resetPasswordToken) {
        this.resetPasswordToken = resetPasswordToken;
    }

    public Date getResertSendDate() {
        return resertSendDate;
    }

    public void setResertSendDate(Date resertSendDate) {
        this.resertSendDate = resertSendDate;
    }

    public boolean isFacebook() {
        return facebook.equals('Y');
    }

    public void setFacebook(Character facebook) {
        this.facebook = facebook;
    }
}

