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

    @Override
    public String toString() {
        return "ReaderEntity{" +
                "id=" + id +
                '}';
    }
}
