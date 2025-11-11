/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.curso.service;
import br.com.ifba.curso.dao.CursoDAO;
import br.com.ifba.curso.entity.Curso;
import jakarta.persistence.EntityManager;
import java.util.List;

/**
 * @author luiza
 * Camada de serviço que conecta a interface Swing ao DAO.
 * Aqui centralizamos a lógica de negócio e chamadas ao banco de dados.
 * 
 * Métodos:
 *  - salvarCurso
 *  - listarCursos
 *  - removerCurso
 *  - atualizarCurso
 *  - buscarPorNome
 */
public class CursoService {

    private final CursoDAO cursoDao = new CursoDAO();

    // ---------- SALVAR ----------
    public void salvarCurso(Curso curso) {
        cursoDao.save(curso);
    }

    // ---------- ATUALIZAR ----------
    public void atualizarCurso(Curso curso) {
        cursoDao.update(curso);
    }

    // ---------- REMOVER ----------
    public void removerCurso(Curso curso) {
        cursoDao.delete(curso);
    }

    // ---------- LISTAR ----------
    public List<Curso> listarCursos() {
        return cursoDao.findAll();
    }

    // ---------- BUSCAR POR NOME ----------
    public Curso buscarPorNome(String nome) {
    return cursoDao.buscarPorNome(nome);
    }
}
