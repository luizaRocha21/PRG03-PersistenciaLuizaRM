/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.curso.view;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;

/**
 * Renderiza botões "Editar" e "Remover" estilizados dentro da JTable.
 * Versão sem ícones (evita erro de ImageIcon nulo).
 * 
 * @author luiza
 */
public class RenderizadorBotao extends JButton implements TableCellRenderer {

    private boolean hover = false;
    private String tipoBotao; // "Editar" ou "Remover"

    public RenderizadorBotao(String text) {
        setText(text);
        setOpaque(false);
        setFocusPainted(false);
        setForeground(Color.WHITE);
        setFont(new Font("Segoe UI", Font.BOLD, 12));
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Efeito de hover suave
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                hover = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hover = false;
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        // Anti-serrilhado
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Define cores base
        Color corBase;
        Color corHover;
        Color sombra;

        if ("Remover".equalsIgnoreCase(tipoBotao)) {
            corBase = new Color(200, 50, 50);
            corHover = new Color(230, 80, 80);
            sombra = new Color(150, 0, 0, 100);
        } else {
            corBase = new Color(50, 100, 200);
            corHover = new Color(80, 130, 230);
            sombra = new Color(0, 0, 100, 100);
        }

        int width = getWidth();
        int height = getHeight();

        // desenha sombra
        g2.setColor(sombra);
        g2.fillRoundRect(2, 4, width - 4, height - 4, 12, 12);

        // desenha fundo principal
        g2.setColor(hover ? corHover : corBase);
        g2.fillRoundRect(0, 0, width - 4, height - 4, 12, 12);

        // desenha borda
        g2.setColor(Color.WHITE);
        g2.drawRoundRect(0, 0, width - 5, height - 5, 12, 12);

        // desenha texto
        FontMetrics fm = g2.getFontMetrics();
        int textX = (width - fm.stringWidth(getText())) / 2;
        int textY = (height + fm.getAscent() - fm.getDescent()) / 2 - 2;
        g2.setColor(Color.WHITE);
        g2.drawString(getText(), textX, textY);

        g2.dispose();
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        tipoBotao = (value == null) ? "" : value.toString();
        setText(tipoBotao);
        return this;
    }
}
