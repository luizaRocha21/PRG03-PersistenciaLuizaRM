/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.curso.view;

/**
 * Classe responsável por renderizar os botões "Editar" e "Remover"
 * dentro da JTable da tela CursoListar.
 * Define a aparência visual dos botões em cada célula.
 *
 * @author luiza
 */

import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class RenderizadorBotao extends JButton implements TableCellRenderer {

    public RenderizadorBotao(String text) {
        setText(text);
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        setText((value == null) ? "" : value.toString());
        return this;
    }
}

