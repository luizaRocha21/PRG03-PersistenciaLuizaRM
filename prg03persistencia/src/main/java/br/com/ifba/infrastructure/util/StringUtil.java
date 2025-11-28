/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.infrastructure.util;

/**
 * Classe utilitária para validações de String.
 * Utilizada por Controllers e Services.
 * @author luiza
 */
public class StringUtil {

    // Verifica se é nulo ou vazio
    public static boolean isNullOrEmpty(String s) {
        return s == null || s.trim().isEmpty();
    }

    // Verifica tamanho mínimo
    public static boolean hasMinLength(String s, int minLength) {
        return s != null && s.trim().length() >= minLength;
    }

    // Verifica se contém apenas letras e acentos
    public static boolean isAlphabetic(String s) {
        return s != null && s.matches("^[A-Za-zÀ-ÿ\\s]+$");
    }
}
