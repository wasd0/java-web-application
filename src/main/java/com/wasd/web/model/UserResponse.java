package com.wasd.web.model;

import java.util.Date;

public class UserResponse {
    private Long id;
    private String name;
    private Date registrationDate;

    public Long getId() {
        return id;
    }

    public UserResponse setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserResponse setName(String name) {
        this.name = name;
        return this;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public UserResponse setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
        return this;
    }
}
