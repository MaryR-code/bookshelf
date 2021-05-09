package com.example.bookshelf.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "books")
public class BookEntity implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 150, nullable = false)
    @NotBlank
    private String title;

    @Column(length = 2000)
    private String description;

    @ManyToOne(optional = false)    // обязательный параметр
    @JoinColumn(name = "author_id", nullable = false)
    @NotNull(message = "Please select author")
    private AuthorEntity author;

    @Column(nullable = false)
    @NotNull
    private int year;

    @Column(length = 13)
    private String isbn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AuthorEntity getAuthor() {
        return author;
    }

    public void setAuthor(AuthorEntity author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
