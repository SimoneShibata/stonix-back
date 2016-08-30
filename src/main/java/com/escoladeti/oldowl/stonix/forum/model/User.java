package com.escoladeti.oldowl.stonix.forum.model;

import com.google.common.base.Objects;

import javax.persistence.Entity;
import java.util.Date;

/**
 * Created by felipe on 26/08/16.
 */
@Entity
public class User extends SuperEntity {

    private String name;
    private Date birth;
    private String email;
    private Integer xp;
    private Integer reputation;
    private String password;
    private String image;
    private boolean authenticated;

    public User() {
        this.xp = 0;
        this.reputation = 0;
        this.authenticated = false;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Integer getXp() {
        return xp;
    }

    public void setXp(Integer xp) {
        this.xp = xp;
    }

    public Integer getReputation() {
        return reputation;
    }

    public void setReputation(Integer reputation) {
        this.reputation = reputation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User that = (User) o;
        return Objects.equal(this.getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getDead());
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", birth=" + birth +
                ", xp=" + xp +
                ", reputation=" + reputation +
                '}';
    }
}
