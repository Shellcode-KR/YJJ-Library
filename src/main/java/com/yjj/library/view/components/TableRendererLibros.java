package com.yjj.library.view.components;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class TableRendererLibros extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column) {

        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        c.setBackground(isSelected ? table.getSelectionBackground() : Color.WHITE);

        // === Categoría ===
        if (column == 3) {
            JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            p.setBackground(isSelected ? table.getSelectionBackground() : Color.WHITE);

            JLabel chip = new JLabel(value.toString());
            chip.setOpaque(true);
            chip.setFont(new Font("Segoe UI", Font.BOLD, 12));
            chip.setForeground(Color.DARK_GRAY);
            chip.setBorder(BorderFactory.createEmptyBorder(3, 10, 3, 10));

            switch (value.toString()) {
                case "Matemáticas":
                    chip.setBackground(new Color(225, 245, 254));
                    break;
                case "Física":
                    chip.setBackground(new Color(232, 245, 233));
                    break;
                case "Química":
                    chip.setBackground(new Color(255, 249, 196));
                    break;
                case "Informática":
                    chip.setBackground(new Color(243, 229, 245));
                    break;
                default:
                    chip.setBackground(new Color(230, 230, 230));
                    break;
            }

            p.add(chip);
            return p;
        }

        // === Disponibles ===
        if (column == 5) {
            JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            p.setBackground(isSelected ? table.getSelectionBackground() : Color.WHITE);

            String[] parts = value.toString().split("/");
            int actual = Integer.parseInt(parts[0].trim());
            int total = Integer.parseInt(parts[1].trim());

            JLabel lbl = new JLabel(value.toString());
            lbl.setOpaque(true);
            lbl.setFont(new Font("Segoe UI", Font.BOLD, 12));
            lbl.setForeground(Color.WHITE);
            lbl.setHorizontalAlignment(SwingConstants.CENTER);
            lbl.setBorder(BorderFactory.createEmptyBorder(3, 10, 3, 10));

            if (actual == 0) {
                lbl.setBackground(new Color(220, 53, 69));
            } else if (actual < (total / 2)) {
                lbl.setBackground(new Color(255, 193, 7));
            } else {
                lbl.setBackground(new Color(40, 167, 69));
            }

            p.add(lbl);
            return p;
        }

        // === Acciones ===
        if (column == 6) {
            JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
            p.setBackground(isSelected ? table.getSelectionBackground() : Color.WHITE);

            JLabel editIcon = new JLabel(new FlatSVGIcon("icons/edit.svg", 16, 16));

            editIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));

            JLabel deleteIcon = new JLabel(new FlatSVGIcon("icons/trash.svg", 16, 16));
            deleteIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));

            p.add(editIcon);
            p.add(deleteIcon);
            return p;
        }

        return c;
    }
}
