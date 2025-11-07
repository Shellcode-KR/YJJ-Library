package com.yjj.library.view.components;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class ButtonRenderer extends JButton implements TableCellRenderer {

    public ButtonRenderer() {
        setText("Registrar");
        setFocusPainted(false);
        setFont(new Font("Segoe UI", Font.BOLD, 12));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus,
                                                   int row, int column) {
        if (isSelected) {
            setBackground(table.getSelectionBackground());
        } else {
            setBackground(Color.WHITE);
        }
        return this;
    }
}
