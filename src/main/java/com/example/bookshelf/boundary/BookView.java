package com.example.bookshelf.boundary;

import com.example.bookshelf.control.BookControl;
import com.example.bookshelf.model.BookEntity;

import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped     // пока пользователь находится на странице (между Request и Session)
public class BookView implements Serializable {
    @Inject
    private BookControl control;
    private long bookId;
    private BookEntity book;

    public void open() {
        book = control.find(bookId);
    }

    public String save() {
        control.update(book);
        return "/book?faces-redirect=true&includeViewParams=true";
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public BookEntity getBook() {
        return book;
    }
}
