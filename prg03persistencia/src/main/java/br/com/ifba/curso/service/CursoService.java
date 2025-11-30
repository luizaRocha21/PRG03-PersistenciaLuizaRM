/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.curso.service;
import br.com.ifba.curso.dao.CursoDAO;
import br.com.ifba.curso.entity.Curso;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Camada de regras de negócio.
 * @author luiza
 */
@Service
@RequiredArgsConstructor
public class CursoService implements ICursoService {

    private final CursoDAO cursoDao; // injetado pelo Spring

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
