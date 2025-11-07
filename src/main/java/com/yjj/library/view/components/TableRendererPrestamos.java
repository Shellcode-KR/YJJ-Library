package views.components;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class TableRendererPrestamos extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus,
                                                   int row, int column) {

        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        c.setBackground(isSelected ? table.getSelectionBackground() : Color.WHITE);

        // === Estado (Activo / Vencido) ===
        if (column == 4) {
            JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            p.setBackground(isSelected ? table.getSelectionBackground() : Color.WHITE);

            JLabel chip = new JLabel(value.toString());
            chip.setOpaque(true);
            chip.setFont(new Font("Segoe UI", Font.BOLD, 12));
            chip.setForeground(Color.WHITE);
            chip.setBorder(BorderFactory.createEmptyBorder(3, 10, 3, 10));

            if ("Activo".equalsIgnoreCase(value.toString())) {
                chip.setBackground(new Color(33, 37, 41));
            } else {
                chip.setBackground(new Color(220, 53, 69));
            }

            p.add(chip);
            return p;
        }

        // === Días Restantes ===
        if (column == 5) {
            JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            p.setBackground(isSelected ? table.getSelectionBackground() : Color.WHITE);

            int dias = Integer.parseInt(value.toString());
            JLabel lbl = new JLabel();

            if (dias >= 0) {
                lbl.setText(dias + " días");
                lbl.setForeground(new Color(40, 167, 69));
            } else {
                lbl.setText(Math.abs(dias) + " días vencido");
                lbl.setForeground(new Color(220, 53, 69));
            }

            lbl.setFont(new Font("Segoe UI", Font.BOLD, 13));
            p.add(lbl);
            return p;
        }

        return c;
    }
}
