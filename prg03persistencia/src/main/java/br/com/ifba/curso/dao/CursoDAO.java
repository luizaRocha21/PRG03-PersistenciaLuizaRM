/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.curso.dao;
import br.com.ifba.curso.entity.Curso;
import br.com.ifba.infrastructure.dao.GenericDao;
import jakarta.persistence.NoResultException;
import org.springframework.stereotype.Repository;

/**
 * DAO específico para Curso.
 * Usa GenericDao para operações CRUD básicas.
 * @author luiza
 */
@Repository
public class CursoDAO extends GenericDao<Curso> {

    public CursoDAO() {
        super(Curso.class);
    }

    /**
     * Busca curso pelo nome com JOIN FETCH para carregar alunos.
     */
    public Curso buscarPorNome(String nome) {
        try {
            return em.createQuery(
                "SELECT c FROM Curso c LEFT JOIN FETCH c.alunos WHERE c.nome = :nome",
                Curso.class
            )
            .setParameter("nome", nome)
            .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
