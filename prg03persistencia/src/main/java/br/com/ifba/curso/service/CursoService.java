/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.curso.service;
import br.com.ifba.curso.dao.CursoDAO;
import br.com.ifba.curso.entity.Curso;
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
public class CursoService implements ICursoService{

    private final CursoDAO cursoDao = new CursoDAO();

    // ---------- SALVAR ----------
    @Override
    public void salvarCurso(Curso curso) {
        cursoDao.save(curso);
    }

    // ---------- ATUALIZAR ----------
    @Override
    public void atualizarCurso(Curso curso) {
        cursoDao.update(curso);
    }

    // ---------- REMOVER ----------
    @Override
    public void removerCurso(Curso curso) {
        cursoDao.delete(curso);
    }

    // ---------- LISTAR ----------
    @Override
    public List<Curso> listarCursos() {
        return cursoDao.findAll();
    }

    // ---------- BUSCAR POR NOME ----------
    @Override
    public Curso buscarPorNome(String nome) {
    return cursoDao.buscarPorNome(nome);
    }
}
