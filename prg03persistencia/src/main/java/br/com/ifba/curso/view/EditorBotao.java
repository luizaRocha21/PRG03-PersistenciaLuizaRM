/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.curso.view;
import br.com.ifba.config.AppConfig;
import br.com.ifba.curso.controller.CursoController;
import br.com.ifba.curso.entity.Curso;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Editor de célula personalizado para inserir botões "Editar" e "Remover"
 * dentro da JTable do CursoListar.
 * Usa Spring para obter o CursoController, evitando instanciar services na view.
 * @author luiza
 */
public class EditorBotao extends DefaultCellEditor {

    private final JButton button;           // Botão exibido na célula
    private boolean clicked;                // Indica se foi clicado
    private int row;                        // Linha da tabela
    private final JTable table;             // Tabela onde está o botão
    private final String tipo;              // "editar" ou "remover"

    // 🔹 ApplicationContext do Spring para buscar o controller
    private static final ApplicationContext context =
            new AnnotationConfigApplicationContext(AppConfig.class);

    // 🔹 Controller obtido diretamente do Spring
    private final CursoController controller =
            context.getBean(CursoController.class);

    /**
     * Construtor padrão.
     *
     * @param checkBox padrão do DefaultCellEditor
     * @param table tabela alvo
     * @param tipo tipo do botão ("editar" ou "remover")
     */
    public EditorBotao(JCheckBox checkBox, JTable table, String tipo) {
        super(checkBox);
        this.table = table;
        this.tipo = tipo;

        this.button = new JButton();
        button.setOpaque(true);
        button.setFocusPainted(false);
        button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        // 🔹 Ação do botão conforme o tipo
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

    /**
     * Remove um curso da tabela e do banco via Controller/Spring.
     */
    private void removerCurso(int row) {

        String nome = (String) table.getValueAt(row, 0);

        int opt = JOptionPane.showConfirmDialog(button,
                "Deseja realmente remover o curso \"" + nome + "\"?",
                "Confirmação de Remoção",
                JOptionPane.YES_NO_OPTION);

        if (opt != JOptionPane.YES_OPTION) return;

        try {

            Curso curso = controller.buscarPorNome(nome);

            if (curso != null) {
                controller.removerCurso(curso);       // 🔹 Remoção via Spring
            }

            ((DefaultTableModel) table.getModel()).removeRow(row);

            JOptionPane.showMessageDialog(button,
                    "Curso \"" + nome + "\" removido com sucesso!",
                    "Removido",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(button,
                    "Erro ao remover curso: " + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Abre a tela CursoEditar com o curso selecionado.
     */
    private void editarCurso(int row) {

        try {

            String nome = (String) table.getValueAt(row, 0);
            Curso curso = controller.buscarPorNome(nome);

            if (curso != null) {

                CursoEditar telaEditar = new CursoEditar(curso);
                telaEditar.setTitle("Editar Curso - " + nome);
                telaEditar.setVisible(true);

            } else {
                JOptionPane.showMessageDialog(button,
                        "Curso não encontrado no banco de dados.",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(button,
                    "Erro ao abrir a tela de edição: " + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
