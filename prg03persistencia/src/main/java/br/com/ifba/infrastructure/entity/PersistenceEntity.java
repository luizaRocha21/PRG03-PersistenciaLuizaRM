/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.infrastructure.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Classe base de entidades com ID gerado automaticamente.
 * @author luiza
 */
@MappedSuperclass
@Getter     // Lombok gera getter
@Setter     // Lombok gera setter
public abstract class PersistenceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
