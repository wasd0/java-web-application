package com.wasd.web.model.user;

import java.util.Date;

public class UserRequest {
    private Long id;
    private String name;
    private Date registrationDate;

    public Long getId() {
        return id;
    }

    public UserRequest setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserRequest setName(String name) {
        this.name = name;
        return this;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public UserRequest setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
        return this;
    }
}
