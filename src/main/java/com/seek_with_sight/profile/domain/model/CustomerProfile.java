package com.seek_with_sight.profile.domain.model;

import com.seek_with_sight.domain.model.BaseDomainModel;
import com.seek_with_sight.user.domain.model.User;

public class CustomerProfile extends BaseDomainModel {
    private String firstName;
    private String lastName;
    private String phone;
    private User user;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
