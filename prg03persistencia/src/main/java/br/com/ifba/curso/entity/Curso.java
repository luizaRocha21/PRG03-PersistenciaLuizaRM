/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.curso.entity;
import br.com.ifba.infrastructure.entity.PersistenceEntity;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * Entidade Curso
 * @author luiza
 */
@Entity
@Table(name = "curso")
@Data                       
@NoArgsConstructor          
@AllArgsConstructor
public class Curso extends PersistenceEntity {

    private String nome;
    private String descricao;
    private String professor;

    @ElementCollection
    @CollectionTable(name = "curso_alunos", joinColumns = @JoinColumn(name = "curso_id"))
    @Column(name = "nome_aluno")
    private List<String> alunos = new ArrayList<>(); // continua inicializada
}
