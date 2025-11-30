/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.curso.controller;
import br.com.ifba.curso.entity.Curso;
import br.com.ifba.curso.service.ICursoService;
import br.com.ifba.infrastructure.util.StringUtil;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

/**
 * Controller gerenciado pelo Spring.
 * @author luiza
 */
@Controller
@RequiredArgsConstructor // injeta dependências finais automaticamente
public class CursoController implements ICursoController {

    private final ICursoService cursoService; // injetado pelo Spring

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

    // Validações
    private void validarCurso(Curso curso) {
        if (curso == null)
            throw new IllegalArgumentException("Curso não pode ser nulo.");

        if (StringUtil.isNullOrEmpty(curso.getNome()))
            throw new IllegalArgumentException("O nome é obrigatório.");

        if (!StringUtil.hasMinLength(curso.getNome(), 3))
            throw new IllegalArgumentException("O nome deve ter ao menos 3 caracteres.");

        if (StringUtil.isNullOrEmpty(curso.getProfessor()))
            throw new IllegalArgumentException("Professor é obrigatório.");
    }
}
