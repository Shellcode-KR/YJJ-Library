package com.yjj.library.view.components;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import com.formdev.flatlaf.extras.*;

public class TableRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        // === ESTADO ===
        if (column == 5) { // Columna Estado
            JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            panel.setOpaque(true);
            panel.setBackground(isSelected ? table.getSelectionBackground() : Color.WHITE);

            JLabel chip = new JLabel(value.toString());
            chip.setOpaque(true);
            chip.setForeground(Color.WHITE);
            chip.setFont(new Font("Segoe UI", Font.BOLD, 12));
            chip.setHorizontalAlignment(SwingConstants.CENTER);
            chip.setBorder(BorderFactory.createEmptyBorder(3, 10, 3, 10));

            if ("Activo".equalsIgnoreCase(value.toString())) {
                chip.setBackground(new Color(50, 150, 90)); // verde
            } else {
                chip.setBackground(new Color(170, 170, 170)); // gris
            }

            panel.add(chip);
            return panel;
        }

        // === ACCIONES ===
        if (column == 6) { // Columna Acciones
            JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
            panel.setOpaque(true);
            panel.setBackground(isSelected ? table.getSelectionBackground() : Color.WHITE);

            JLabel editIcon = new JLabel(new FlatSVGIcon("icons/edit.svg", 16, 16));
            
            editIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));

            JLabel deleteIcon = new JLabel(new FlatSVGIcon("icons/trash.svg", 16, 16));
            deleteIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));

            panel.add(editIcon);
            panel.add(deleteIcon);

            return panel;
        }

        // === POR DEFECTO ===
        if (isSelected) {
            c.setBackground(table.getSelectionBackground());
        } else {
            c.setBackground(Color.WHITE);
        }

        return c;
    }
}
