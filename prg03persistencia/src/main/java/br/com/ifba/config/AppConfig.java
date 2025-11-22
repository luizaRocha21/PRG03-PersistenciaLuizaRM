/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.config;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Configuração do Spring: faz scan do projeto para encontrar Beans.
 * @author luiza
 */

@Configuration
@ComponentScan(basePackages = "br.com.ifba")
public class AppConfig {
    // Classe vazia: apenas habilita component scanning.
}


