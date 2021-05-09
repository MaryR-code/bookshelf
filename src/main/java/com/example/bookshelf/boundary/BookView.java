package com.example.bookshelf.boundary;

import com.example.bookshelf.control.BookControl;
import com.example.bookshelf.control.ReaderControl;
import com.example.bookshelf.control.ReservationControl;
import com.example.bookshelf.model.BookEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

@Named
@ViewScoped     // пока пользователь находится на странице (между Request и Session)
public class BookView implements Serializable {
    private static final Logger LOG = LoggerFactory.getLogger(BookView.class);
    @Inject
    private HttpServletRequest request;
    @Inject
    private ReaderControl readerControl;
    @Inject
    private ReservationControl reservationControl;
    @Inject
    private BookControl control;
    private Long bookId;
    private BookEntity book;

    public String reserve() {
        var loginName = request.getRemoteUser();
        var reader = readerControl.findOrCreateReader(loginName);
        reservationControl.reserve(book, reader);
        return null;
    }

    public void open() {
        if (bookId == null) {
            book = new BookEntity();
        } else {
            book = control.find(bookId);
        }
    }

    public String save() {
        var username = request.getRemoteUser();
        if (bookId == null) {
            control.create(book);
            bookId = book.getId();
            LOG.trace("New book ID ({}) created, user '{}'.", bookId, username);
        } else {
            control.update(book);
            LOG.trace("Book ID ({}) updated, user '{}'.", bookId, username);
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
