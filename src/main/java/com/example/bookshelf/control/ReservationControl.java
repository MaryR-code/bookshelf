package com.example.bookshelf.control;

import com.example.bookshelf.model.BookEntity;
import com.example.bookshelf.model.ReaderEntity;
import com.example.bookshelf.model.ReservationEntity;
import com.example.bookshelf.model.ReservationStatus;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ReservationControl {
    @PersistenceContext
    private EntityManager em;

    public void reserve(BookEntity book, ReaderEntity reader) {
        if (reservationExists(book, reader)) {
            return;
        }
        var res = new ReservationEntity();
        res.setBook(book);
        res.setReader(reader);
        res.setStatus(ReservationStatus.ACTIVE);
        em.persist(res);

    }

    private boolean reservationExists(BookEntity book, ReaderEntity reader) {
        var count = em.createQuery("select count(r) from ReservationEntity r " +
                           "where r.book = :book and r.reader = :reader and r.status <> 'CLOSED'", Long.class)
                .setParameter("book", book)
                .setParameter("reader", reader)
                .getSingleResult();
        return count > 0;
    }
}
