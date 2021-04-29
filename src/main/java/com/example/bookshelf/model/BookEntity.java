package com.example.bookshelf.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class BookEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 150, nullable = false)
    @NotBlank
    private String title;

    @Column(length = 2000)
    private String description;

//    @Column(length = 200, nullable = false)
//    private String author;

    @ManyToOne(optional = false)
    @JoinColumn(name = "author_id", nullable = false)
    @NotNull(message = "Please select author")
    private AuthorEntity author;

    @Column(nullable = false)
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

//    public String getAuthor() {
//        return author;
//    }
//
//    public void setAuthor(String author) {
//        this.author = author;
//    }

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
