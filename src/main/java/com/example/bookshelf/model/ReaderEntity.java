package com.example.bookshelf.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "readers")
public class ReaderEntity implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "login_name", nullable = false, unique = true)
    private String loginName;

    @Column
    private String fullName;

    @Column
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "ReaderEntity{" +
                "id=" + id +
                '}';
    }
}
