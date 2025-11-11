/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.infrastructure.persistence;

/**
 * Classe utilitária responsável por fornecer instâncias de EntityManager
 * @author luiza
 */

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtil {
    
    private static final EntityManagerFactory emf = 
            Persistence.createEntityManagerFactory("ifbaPU"); // nome da sua persistence-unit
    
    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}

