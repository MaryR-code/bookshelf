package com.example.bookshelf.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "authors")
public class AuthorEntity implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 150, nullable = false)
    private String name;

    @Column(length = 2000)
    private String bio;

    @Column(nullable = false)
    private LocalDate born;

    @Column
    private LocalDate died;

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

    public LocalDate getBorn() {
        return born;
    }

    public void setBorn(LocalDate born) {
        this.born = born;
    }

    public LocalDate getDied() {
        return died;
    }

    public void setDied(LocalDate died) {
        this.died = died;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AuthorEntity)) return false;
        AuthorEntity that = (AuthorEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "AuthorEntity{" +
                "id=" + id +
                '}';
    }
}
