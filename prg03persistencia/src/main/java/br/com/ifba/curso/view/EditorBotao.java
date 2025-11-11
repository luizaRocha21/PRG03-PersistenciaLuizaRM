/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.curso.view;
import br.com.ifba.curso.entity.Curso;
import br.com.ifba.curso.service.CursoService;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class EditorBotao extends DefaultCellEditor {
    private final JButton button;
    private boolean clicked;
    private int row;
    private final JTable table;
    private final CursoService service;
    private final String tipo; // "editar" ou "remover"

    public EditorBotao(JCheckBox checkBox, JTable table, String tipo) {
        super(checkBox);
        this.table = table;
        this.tipo = tipo;
        this.service = new CursoService();

        button = new JButton();
        button.setOpaque(true);
        button.setFocusPainted(false);
        button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        // Define ação específica conforme tipo
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (clicked) {
                    if ("editar".equalsIgnoreCase(tipo)) {
                        editarCurso(row);
                    } else if ("remover".equalsIgnoreCase(tipo)) {
                        removerCurso(row);
                    }
                }
                clicked = false;
                fireEditingStopped();
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(
            JTable table, Object value, boolean isSelected, int row, int col) {
        this.row = row;
        button.setText(value == null ? "" : value.toString());
        clicked = true;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        return button.getText();
    }

    private void removerCurso(int row) {
        String nome = (String) table.getValueAt(row, 0);
        int opt = JOptionPane.showConfirmDialog(button,
                "Deseja realmente remover o curso \"" + nome + "\"?",
                "Confirmação de Remoção", JOptionPane.YES_NO_OPTION);

        if (opt == JOptionPane.YES_OPTION) {
            try {
                Curso curso = service.buscarPorNome(nome);
                if (curso != null) {
                    service.removerCurso(curso);
                }
                ((DefaultTableModel) table.getModel()).removeRow(row);
                JOptionPane.showMessageDialog(button,
                        "Curso \"" + nome + "\" removido com sucesso!",
                        "Removido", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(button,
                        "Erro ao remover curso: " + e.getMessage(),
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void editarCurso(int row) {
        try {
            String nome = (String) table.getValueAt(row, 0);
            Curso curso = service.buscarPorNome(nome);

            if (curso != null) {
                CursoEditar telaEditar = new CursoEditar(curso);
                telaEditar.setTitle("Editar Curso - " + nome);
                telaEditar.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(button,
                        "Curso não encontrado no banco de dados.",
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(button,
                    "Erro ao abrir a tela de edição: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
