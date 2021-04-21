package com.example.bookshelf;

import com.example.bookshelf.model.BookEntity;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Named
@RequestScoped
public class SearchBookBean {
    @PersistenceContext
    private EntityManager em;       // связь с БД
    private List<BookEntity> books;
    private String term;

    @PostConstruct      // предварительно вызывает конструктор, затем сам метод
    public void init() {
        search();
    }

    public void search() {
        if (term == null || term.isBlank()) {
            books = em.createQuery("select b from BookEntity b", BookEntity.class)      // язык JPQL
                    .setMaxResults(20)
                    .getResultList();
        } else {
            books = em.createQuery("select b from BookEntity b where upper(b.title) like :term or upper(b.author) like :term", BookEntity.class)
                    .setMaxResults(20)
                    .setParameter("term", "%" + term.toUpperCase() + "%")
                    .getResultList();
        }
    }

    public List<BookEntity> getBooks() {
        return books;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }
}






