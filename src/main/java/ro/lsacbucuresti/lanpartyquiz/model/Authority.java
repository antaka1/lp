package ro.lsacbucuresti.lanpartyquiz.model;

import ro.lsacbucuresti.lanpartyquiz.enums.Roles;

import javax.persistence.*;

/**
 * Created by cristi on 05 - October - 2017
 */
@Entity
@Table(name = "authority")
public class Authority {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private Roles role;

    public Authority() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }
}
