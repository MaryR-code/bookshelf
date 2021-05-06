package com.example.bookshelf.boundary;

import com.example.bookshelf.control.AuthorControl;
import com.example.bookshelf.model.AuthorEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

@Named
@ViewScoped
public class AuthorView implements Serializable {
    private static final Logger LOG = LoggerFactory.getLogger(AuthorView.class);
    @Inject
    private HttpServletRequest request;
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
        var username = request.getRemoteUser();
        if (authorId == null) {
            control.create(author);
            authorId = author.getId();
            LOG.trace("New author ID ({}) created, user '{}'.", authorId, username);
        } else {
            control.update(author);
            LOG.trace("Author ID ({}) updated, user '{}'.", authorId, username);
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
