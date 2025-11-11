/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.curso.dao;
import br.com.ifba.curso.entity.Curso;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;
import static br.com.ifba.infrastructure.persistence.JpaUtil.getEntityManager;
import jakarta.persistence.EntityManager;

/**
 * @author luiza
 * Classe DAO responsável pela comunicação com o banco de dados (via JPA/Hibernate).
 */
public class CursoDAO {

    private static final EntityManagerFactory emf = 
            Persistence.createEntityManagerFactory("ifbaPU");

    // ---------- SALVAR ----------
    public void save(Curso curso) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(curso);
        em.getTransaction().commit();
        em.close();
    }

    // ---------- ATUALIZAR ----------
    public void update(Curso curso) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(curso);
        em.getTransaction().commit();
        em.close();
    }

    // ---------- REMOVER ----------
    public void delete(Curso curso) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Curso c = em.find(Curso.class, curso.getId());
        if (c != null) {
            em.remove(c);
        }
        em.getTransaction().commit();
        em.close();
    }

    // ---------- LISTAR TODOS ----------
    public List<Curso> findAll() {
    EntityManager em = emf.createEntityManager();
    List<Curso> cursos = em.createQuery(
        "SELECT DISTINCT c FROM Curso c LEFT JOIN FETCH c.alunos", Curso.class
    ).getResultList();
    em.close();
    return cursos;
}

    // ---------- BUSCAR POR NOME ----------
    public Curso buscarPorNome(String nome) {
    EntityManager em = getEntityManager();
    try {
        return em.createQuery(
                "SELECT c FROM Curso c LEFT JOIN FETCH c.alunos WHERE c.nome = :nome", Curso.class)
                .setParameter("nome", nome)
                .getSingleResult();
    } catch (jakarta.persistence.NoResultException e) {
        return null;
    } finally {
        em.close();
    }
    }
}
