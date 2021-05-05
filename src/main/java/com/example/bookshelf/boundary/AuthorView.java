package com.example.bookshelf.boundary;

import com.example.bookshelf.control.AuthorControl;
import com.example.bookshelf.model.AuthorEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class AuthorView implements Serializable {
    private static final Logger LOG = LoggerFactory.getLogger(AuthorView.class);
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
        LOG.trace("Author saving...");
        if (authorId == null) {
            control.create(author);
            authorId = author.getId();
            LOG.debug("New author ID = {} created.", authorId);
        } else {
            control.update(author);
            LOG.debug("Author ID = {} updated.", authorId);
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
