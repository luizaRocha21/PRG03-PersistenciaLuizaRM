/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.curso.controller;
import br.com.ifba.curso.entity.Curso;
import java.util.List;

/**
 * @author luiza
 * Contrato do Controller.
 */
public interface ICursoController {
    void salvarCurso(Curso curso);
    void atualizarCurso(Curso curso);
    void removerCurso(Curso curso);
    List<Curso> listarCursos();
    Curso buscarPorNome(String nome);
}
