package com.example.bookshelf.control;

import com.example.bookshelf.model.ReaderEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

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
}
