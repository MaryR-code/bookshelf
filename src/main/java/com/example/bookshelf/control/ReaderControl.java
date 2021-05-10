package com.example.bookshelf.control;

import com.example.bookshelf.model.AuthorEntity;
import com.example.bookshelf.model.ReaderEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ReaderControl {
    @PersistenceContext
    private EntityManager em;

    public ReaderEntity findOrCreateReader(String loginName) {
        var reader = em.createQuery("select r from ReaderEntity r where r.loginName = :loginName", ReaderEntity.class)
                .setParameter("loginName", loginName)
                .getResultStream()
                .findFirst();
        if (reader.isEmpty()) {
            var result = new ReaderEntity();
            result.setLoginName(loginName);
            em.persist(result);
            return result;
        } else {
            return reader.get();
        }
    }

    public List<ReaderEntity> search(String term) {
        if (term == null || term.isBlank()) {
            return em.createQuery("select r from ReaderEntity r", ReaderEntity.class)
                    .setMaxResults(20)
                    .getResultList();
        } else {
            return em.createQuery("select r from ReaderEntity r " +
                    "where upper(r.loginName) like :term", ReaderEntity.class)
                    .setMaxResults(20)
                    .setParameter("term", "%" + term.toUpperCase() + "%")
                    .getResultList();
        }
    }

    public void update(ReaderEntity reader) {
        em.merge(reader);
    }
}
