/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.curso.controller;
import br.com.ifba.curso.entity.Curso;
import br.com.ifba.curso.service.ICursoService;
import br.com.ifba.infrastructure.util.StringUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author luiza
 * Controller gerenciado pelo Spring.
 * Atua entre a View e a camada Service.
 */
@Controller
public class CursoController implements ICursoController {

    // Injeção de dependência via Spring
    @Autowired
    private ICursoService cursoService;

    @Override
    public void salvarCurso(Curso curso) {
        validarCurso(curso);
        cursoService.salvarCurso(curso);
    }

    @Override
    public void atualizarCurso(Curso curso) {
        validarCurso(curso);
        cursoService.atualizarCurso(curso);
    }

    @Override
    public void removerCurso(Curso curso) {
        cursoService.removerCurso(curso);
    }

    @Override
    public List<Curso> listarCursos() {
        return cursoService.listarCursos();
    }

    @Override
    public Curso buscarPorNome(String nome) {
        return cursoService.buscarPorNome(nome);
    }

    /**
     * Validações básicas antes de enviar para o Service.
     */
    private void validarCurso(Curso curso) {
        if (curso == null)
            throw new IllegalArgumentException("O curso não pode ser nulo.");

        if (StringUtil.isNullOrEmpty(curso.getNome()))
            throw new IllegalArgumentException("O nome do curso é obrigatório.");

        if (!StringUtil.hasMinLength(curso.getNome(), 3))
            throw new IllegalArgumentException("O nome deve ter ao menos 3 caracteres.");

        if (StringUtil.isNullOrEmpty(curso.getProfessor()))
            throw new IllegalArgumentException("O professor é obrigatório.");
    }
}
