/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.curso.view;

/**
 * Classe responsável por controlar as ações dos botões "Editar" e "Remover"
 * da JTable em CursoListar.
 * Ao clicar em "Editar", abre a tela CursoEditar; ao clicar em "Remover",
 * exibe uma confirmação e exclui o curso selecionado.
 *
 * @author luiza
 */



import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

public class EditorBotao extends DefaultCellEditor {

    private JButton button;
    private String label;
    private boolean clicked;
    private int row;
    private JTable table;

    public EditorBotao(JCheckBox checkBox, JTable table) {
        super(checkBox);
        this.table = table;

        button = new JButton();
        button.setOpaque(true);

        // Ao clicar no botão, encerramos a edição da célula
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int col) {
        label = (value == null) ? "" : value.toString();
        button.setText(label);
        clicked = true;
        this.row = row;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        if (clicked) {
            if ("Remover".equals(label)) {
                removerCurso(row);
            } else if ("Editar".equals(label)) {
                editarCurso(row);
            }
        }
        clicked = false;
        return label;
    }

    private void removerCurso(int row) {
        String nome = (String) table.getValueAt(row, 0);

        int opt = JOptionPane.showConfirmDialog(button,
                "Deseja realmente remover o curso \"" + nome + "\"?",
                "Confirmação de Remoção", JOptionPane.YES_NO_OPTION);

        if (opt == JOptionPane.YES_OPTION) {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.removeRow(row);
            JOptionPane.showMessageDialog(button,
                    "Curso \"" + nome + "\" removido com sucesso!",
                    "Removido", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void editarCurso(int row) {
        String nome = (String) table.getValueAt(row, 0);
        String descricao = (String) table.getValueAt(row, 1);
        String professor = (String) table.getValueAt(row, 2);

        int opt = JOptionPane.showConfirmDialog(button,
                "Deseja editar o curso \"" + nome + "\"?",
                "Editar Curso", JOptionPane.YES_NO_OPTION);

        if (opt == JOptionPane.YES_OPTION) {
            // Abre a tela de edição e preenche com os dados do curso selecionado
            CursoEditar telaEditar = new CursoEditar();
            telaEditar.setTitle("Editar Curso - " + nome);

            // Preenche os campos da tela de edição
            telaEditar.setVisible(true);
            telaEditar.toFront();

            // Usa SwingUtilities.invokeLater para garantir que os campos já existam
            SwingUtilities.invokeLater(() -> {
                telaEditar.setCurso(nome, descricao, professor);
            });

            JOptionPane.showMessageDialog(button,
                    "A tela de edição foi aberta.\nEdite os dados e clique em 'Salvar Edição'.",
                    "Editar Curso", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    public boolean stopCellEditing() {
        clicked = false;
        return super.stopCellEditing();
    }

    @Override
    protected void fireEditingStopped() {
        super.fireEditingStopped();
    }
}
