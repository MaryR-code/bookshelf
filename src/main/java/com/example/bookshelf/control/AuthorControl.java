package com.example.bookshelf.control;

import com.example.bookshelf.model.AuthorEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class AuthorControl {
    @PersistenceContext
    private EntityManager em;

    public List<AuthorEntity> search(String term) {
        if (term == null || term.isBlank()) {
            return em.createQuery("select a from AuthorEntity a", AuthorEntity.class)
                    .setMaxResults(20)
                    .getResultList();
        } else {
            return em.createQuery("select a from AuthorEntity a " +
                    "where upper(a.name) like :term", AuthorEntity.class)
                    .setMaxResults(20)
                    .setParameter("term", "%" + term.toUpperCase() + "%")
                    .getResultList();
        }
    }

    public AuthorEntity find(Long authorId) {
        return em.find(AuthorEntity.class, authorId);
    }

    public void update(AuthorEntity author) {
        em.merge(author);
    }

    public void create(AuthorEntity author) {
        em.persist(author);
    }
}
