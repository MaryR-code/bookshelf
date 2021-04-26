package com.example.bookshelf.boundary;

import com.example.bookshelf.control.AuthorControl;
import com.example.bookshelf.model.AuthorEntity;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class AuthorView implements Serializable {
    @Inject
    private AuthorControl control;
    private Long authorId;
    private AuthorEntity author;

    public void open() {
        if (authorId == null) {
            author = new AuthorEntity();
        } else {
            author = control.find(authorId);
        }
    }

    public String save() {
        if (authorId == null) {
            control.create(author);
            authorId = author.getId();
        } else {
            control.update(author);
        }
        return "/author?faces-redirect=true&includeViewParams=true";
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public AuthorEntity getAuthor() {
        return author;
    }
}
