package com.example.bookshelf.control;

import com.example.bookshelf.model.BookEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless      // нельзя объявлять поля, каждый метод считается транзакционным
public class BookControl {

    @PersistenceContext
    private EntityManager em;       // связь с БД

    public List<BookEntity> search(String term) {
        if (term == null || term.isBlank()) {
            return em.createQuery("select b from BookEntity b", BookEntity.class)      // язык JPQL
                    .setMaxResults(20)
                    .getResultList();
        } else {
            return em.createQuery("select b from BookEntity b " +
                    "where upper(b.title) like :term or upper(b.author) like :term or upper(b.isbn) like :term", BookEntity.class)
                    .setMaxResults(20)
                    .setParameter("term", "%" + term.toUpperCase() + "%")
                    .getResultList();
        }
    }

    public BookEntity find(long bookId) {
        return em.find(BookEntity.class, bookId);   // найти по ID
    }

    public void update(BookEntity book) {
        em.merge(book);
    }

}
