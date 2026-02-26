package com.erp.dao;

import com.erp.util.JPAUtil;
import jakarta.persistence.EntityManager;
import java.util.List;

public abstract class AbstractDAO<T> {

    private Class<T> entityClass;

    public AbstractDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public T find(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        T entity = em.find(entityClass, id);
        em.close();
        return entity;
    }

    public void save(T entity) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
        em.close();
    }

    public T update(T entity) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        entity = em.merge(entity);
        em.getTransaction().commit();
        em.close();
        return entity;
    }

    public void delete(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();
        T entity = em.find(entityClass, id);
        if(entity != null) {
            em.remove(entity);
        }
        em.getTransaction().commit();
        em.close();
    }

    public List<T> findAll() {
        EntityManager em = JPAUtil.getEntityManager();
        List<T> result = em.createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e", entityClass)
                .getResultList();
        em.close();
        return result;
    }
}
