/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.curso.entity;

import br.com.ifba.infrastructure.entity.PersistenceEntity;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author luiza
 * Entidade Curso – representa um curso com lista de alunos.
 */
@Entity
@Table(name = "curso")
public class Curso extends PersistenceEntity {

    private String nome;
    private String descricao;
    private String professor;

    @ElementCollection
    @CollectionTable(name = "curso_alunos", joinColumns = @JoinColumn(name = "curso_id"))
    @Column(name = "nome_aluno")
    private List<String> alunos = new ArrayList<>();

    // Getters e Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getProfessor() { return professor; }
    public void setProfessor(String professor) { this.professor = professor; }

    public List<String> getAlunos() { return alunos; }
    public void setAlunos(List<String> alunos) { this.alunos = alunos; }
}
