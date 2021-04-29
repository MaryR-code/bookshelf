package com.example.bookshelf.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class AuthorEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 150, nullable = false)
    private String name;

    @Column(length = 2000)
    private String bio;

    @Column(nullable = false)
    private Date born;

    @Column(nullable = true)
    private Date died;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Date getBorn() {
        return born;
    }

    public void setBorn(Date bonn) {
        this.born = bonn;
    }

    public Date getDied() {
        return died;
    }

    public void setDied(Date died) {
        this.died = died;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorEntity that = (AuthorEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
