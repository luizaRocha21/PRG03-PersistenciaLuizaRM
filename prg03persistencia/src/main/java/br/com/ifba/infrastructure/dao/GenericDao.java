/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.infrastructure.dao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * @author luiza
 */

public abstract class GenericDao<T> implements GenericIDao<T> {

    @PersistenceContext
    protected EntityManager em;

    private final Class<T> clazz;

    protected GenericDao(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    @Transactional
    public void save(T entity) {
        em.persist(entity);
    }

    @Override
    @Transactional
    public void update(T entity) {
        em.merge(entity);
    }

    @Override
    @Transactional
    public void delete(T entity) {
        em.remove(em.contains(entity) ? entity : em.merge(entity));
    }

    @Override
    public T findById(Long id) {
        return em.find(clazz, id);
    }

    @Override
    public List<T> findAll() {
        return em.createQuery("FROM " + clazz.getSimpleName(), clazz).getResultList();
    }
}
