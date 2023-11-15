package com.wasd.web.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false, length = 64)
    private String name;
    @Column(name = "registration_date", nullable = false)
    private Date registrationDate;

    public Long getId() {
        return id;
    }

    public User setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public User setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return Objects.equals(getId(), user.getId()) && Objects.equals(getName(), user.getName()) && Objects.equals(getRegistrationDate(), user.getRegistrationDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getRegistrationDate());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", registrationDate=" + registrationDate +
                '}';
    }
}
