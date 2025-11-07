package com.yjj.library.view.components;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TableEditorDevoluciones extends AbstractCellEditor implements TableCellEditor {

    private final JButton btnRegistrar;
    private final JPopupMenu popupMenu;

    public TableEditorDevoluciones(JTable table) {
        // === Botón ===
        btnRegistrar = new JButton("Registrar");
        btnRegistrar.setFocusPainted(false);
        btnRegistrar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnRegistrar.setBackground(Color.WHITE);
        btnRegistrar.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        btnRegistrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // === Menú emergente ===
        popupMenu = new JPopupMenu();

        JMenuItem sinMulta = new JMenuItem("Registrar sin multa ✅");
        JMenuItem conMulta = new JMenuItem("Registrar con multa 💰");
        JMenuItem cancelar = new JMenuItem("Cancelar ❌");

        sinMulta.addActionListener(e -> {
            stopCellEditing();
            JOptionPane.showMessageDialog(table, "✔ Devolución registrada sin multa");
        });

        conMulta.addActionListener(e -> {
            stopCellEditing();
            JOptionPane.showMessageDialog(table, "💰 Devolución registrada con multa");
        });

        cancelar.addActionListener(e -> stopCellEditing());

        popupMenu.add(sinMulta);
        popupMenu.add(conMulta);
        popupMenu.addSeparator();
        popupMenu.add(cancelar);

        // === Click del botón ===
        btnRegistrar.addActionListener(e -> {
            // Determinar coordenadas absolutas
            Rectangle rect = table.getCellRect(table.getEditingRow(), table.getEditingColumn(), true);
            Point tableLocation = table.getLocationOnScreen();

            // Mostrar el popup en coordenadas absolutas
            popupMenu.show(table,
                    rect.x + 100,   // desplazamiento horizontal opcional
                    rect.y + 30);   // desplazamiento vertical opcional
        });
    }

    @Override
    public Object getCellEditorValue() {
        return null;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
        if (isSelected)
            btnRegistrar.setBackground(table.getSelectionBackground());
        else
            btnRegistrar.setBackground(Color.WHITE);
        return btnRegistrar;
    }
}
