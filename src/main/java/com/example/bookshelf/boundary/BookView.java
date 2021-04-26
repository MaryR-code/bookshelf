package com.example.bookshelf.boundary;

import com.example.bookshelf.control.BookControl;
import com.example.bookshelf.model.BookEntity;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped     // пока пользователь находится на странице (между Request и Session)
public class BookView implements Serializable {
    @Inject
    private BookControl control;
    private Long bookId;
    private BookEntity book;

    public void open() {
        if (bookId == null) {
            book = new BookEntity();
        } else {
            book = control.find(bookId);
        }
    }

    public String save() {
        if (bookId == null) {
            control.create(book);
            bookId = book.getId();
        } else {
            control.update(book);
        }
        return "/book?faces-redirect=true&includeViewParams=true";
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public BookEntity getBook() {
        return book;
    }
}
