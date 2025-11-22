/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.infrastructure.dao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

/**
 * @author luiza
 * DAO genérico com operações CRUD básicas.
 * EntityManager manual
 */
public abstract class GenericDao<T> implements GenericIDao<T> {

    // Fábrica de EntityManager da JPA tradicional
    protected static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("ifbaPU");

    // EntityManager utilizado pelos métodos CRUD
    protected EntityManager em = emf.createEntityManager();

    // Tipo da entidade para consultas dinâmicas
    private final Class<T> typeClass;

    protected GenericDao(Class<T> typeClass) {
        this.typeClass = typeClass;
    }

    @Override
    public void save(T entity) {
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
    }

    @Override
    public void update(T entity) {
        em.getTransaction().begin();
        em.merge(entity);
        em.getTransaction().commit();
    }

    @Override
    public void delete(T entity) {
        em.getTransaction().begin();
        // remove exige entidade gerenciada → merge caso necessário
        em.remove(em.contains(entity) ? entity : em.merge(entity));
        em.getTransaction().commit();
    }

    @Override
    public T findById(Long id) {
        return em.find(typeClass, id);
    }

    @Override
    public List<T> findAll() {
        return em.createQuery("FROM " + typeClass.getSimpleName(), typeClass)
                 .getResultList();
    }
}
