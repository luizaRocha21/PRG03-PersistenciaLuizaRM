/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.curso.service;
import br.com.ifba.curso.dao.CursoDAO;
import br.com.ifba.curso.entity.Curso;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author luiza
 * Camada de negócio, gerenciada pelo Spring.
 */
@Service
public class CursoService implements ICursoService {

    // Injeção de dependência do DAO
    @Autowired
    private CursoDAO cursoDao;

    @Override
    public void salvarCurso(Curso curso) {
        cursoDao.save(curso);
    }

    @Override
    public void atualizarCurso(Curso curso) {
        cursoDao.update(curso);
    }

    @Override
    public void removerCurso(Curso curso) {
        cursoDao.delete(curso);
    }

    @Override
    public List<Curso> listarCursos() {
        return cursoDao.findAll();
    }

    @Override
    public Curso buscarPorNome(String nome) {
        return cursoDao.buscarPorNome(nome);
    }
}
