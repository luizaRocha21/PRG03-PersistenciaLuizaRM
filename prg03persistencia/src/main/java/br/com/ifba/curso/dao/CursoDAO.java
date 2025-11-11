/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.curso.dao;
import br.com.ifba.curso.entity.Curso;
import br.com.ifba.infrastructure.dao.GenericDao;
import jakarta.persistence.NoResultException;

/**
 * DAO específico da entidade Curso.
 * Herda métodos CRUD do GenericDao e adiciona consultas personalizadas.
 */
public class CursoDAO extends GenericDao<Curso> {

    public CursoDAO() {
        super(Curso.class);
    }

    // Método específico: busca curso pelo nome
    public Curso buscarPorNome(String nome) {
        try {
            return em.createQuery(
                    "SELECT c FROM Curso c LEFT JOIN FETCH c.alunos WHERE c.nome = :nome",
                    Curso.class)
                    .setParameter("nome", nome)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
