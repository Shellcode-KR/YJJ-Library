package com.yjj.library.view.components;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TableEditorDevoluciones extends AbstractCellEditor implements TableCellEditor {

    private final JButton btnRegistrar = new JButton("Registrar");
    private final JPopupMenu popupMenu;

    public TableEditorDevoluciones() {
        // Menú emergente
        popupMenu = new JPopupMenu();

        JMenuItem sinMulta = new JMenuItem("Registrar sin multa");
        JMenuItem conMulta = new JMenuItem("Registrar con multa");
        JMenuItem cancelar = new JMenuItem("Cancelar");

        sinMulta.addActionListener(e -> JOptionPane.showMessageDialog(null, "✔ Registrado sin multa"));
        conMulta.addActionListener(e -> JOptionPane.showMessageDialog(null, "💰 Registrado con multa"));
        cancelar.addActionListener(e -> JOptionPane.showMessageDialog(null, "❌ Cancelado"));

        popupMenu.add(sinMulta);
        popupMenu.add(conMulta);
        popupMenu.addSeparator();
        popupMenu.add(cancelar);

        // Estilos del botón
        btnRegistrar.setFocusPainted(false);
        btnRegistrar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnRegistrar.setBackground(Color.WHITE);
        btnRegistrar.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        btnRegistrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        btnRegistrar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                popupMenu.show(btnRegistrar, e.getX(), e.getY());
            }
        });
    }

    @Override
    public Object getCellEditorValue() {
        return null;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
        if (isSelected) {
            btnRegistrar.setBackground(table.getSelectionBackground());
        } else {
            btnRegistrar.setBackground(Color.WHITE);
        }
        return btnRegistrar;
    }
}
