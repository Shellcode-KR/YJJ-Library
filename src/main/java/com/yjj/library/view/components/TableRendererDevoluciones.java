package com.yjj.library.view.components;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TableRendererDevoluciones extends DefaultTableCellRenderer {

    private final JPopupMenu popupMenu;

    public TableRendererDevoluciones() {
        // Crear el menú emergente una sola vez
        popupMenu = new JPopupMenu();

        JMenuItem registrarSinMulta = new JMenuItem("Registrar sin multa");
        JMenuItem registrarConMulta = new JMenuItem("Registrar con multa");
        JMenuItem cancelar = new JMenuItem("Cancelar");

        registrarSinMulta.addActionListener(e
                -> JOptionPane.showMessageDialog(null, "Devolución registrada sin multa"));
        registrarConMulta.addActionListener(e
                -> JOptionPane.showMessageDialog(null, "Devolución registrada con multa"));
        cancelar.addActionListener(e
                -> JOptionPane.showMessageDialog(null, "Acción cancelada"));

        popupMenu.add(registrarSinMulta);
        popupMenu.add(registrarConMulta);
        popupMenu.addSeparator();
        popupMenu.add(cancelar);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus,
            int row, int column) {

        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        c.setBackground(isSelected ? table.getSelectionBackground() : Color.WHITE);

        // === Multa ===
        if (column == 4) {
            JLabel lbl = new JLabel(value.toString());
            lbl.setFont(new Font("Segoe UI", Font.BOLD, 13));
            lbl.setHorizontalAlignment(SwingConstants.CENTER);
            lbl.setForeground(value.toString().equals("$0.00")
                    ? new Color(40, 167, 69)
                    : new Color(220, 53, 69));
            lbl.setOpaque(true);
            lbl.setBackground(isSelected ? table.getSelectionBackground() : Color.WHITE);
            return lbl;
        }

        // === Estado ===
        if (column == 5) {
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
                chip.setBackground(new Color(139, 0, 0));
            }

            p.add(chip);
            return p;
        }

        // === Acciones ===
        if (column == 6) {
            return new JLabel(); // vacío, el editor lo manejará
        }

        return c;
    }
}
