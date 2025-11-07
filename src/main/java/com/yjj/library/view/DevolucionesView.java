package com.yjj.library.view;

import com.yjj.library.view.components.TableEditorDevoluciones;
import com.yjj.library.view.components.TableRendererDevoluciones;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class DevolucionesView extends JPanel {

    private JTable tablaDevoluciones;
    private JButton btnPendientes;
    private JButton btnHistorial;
    private JTextField txtBuscar;

    public DevolucionesView() {
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        setBackground(new Color(245, 247, 250));

        // ==================== ENCABEZADO ====================
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBackground(getBackground());
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        JLabel lblTitulo = new JLabel("Gestión de Devoluciones");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));

        JLabel lblSubtitulo = new JLabel("Control de devoluciones de libros prestados");
        lblSubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        lblSubtitulo.setForeground(new Color(100, 100, 100));

        headerPanel.add(lblTitulo);
        headerPanel.add(Box.createVerticalStrut(5));
        headerPanel.add(lblSubtitulo);
        add(headerPanel, BorderLayout.NORTH);

        // ==================== PANEL CENTRAL ====================
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(getBackground());
        centerPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));

        // --- Tarjetas resumen ---
        JPanel cardsPanel = new JPanel(new GridLayout(1, 3, 15, 0));
        cardsPanel.setBackground(getBackground());
        cardsPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));

        cardsPanel.add(crearCard("Devoluciones Pendientes", "2", new Color(255, 193, 7)));
        cardsPanel.add(crearCard("Con Multa", "1", new Color(220, 53, 69)));
        cardsPanel.add(crearCard("Multas Pendientes", "$4.00", new Color(33, 37, 41)));

        centerPanel.add(cardsPanel);

        // ==================== PANEL SUPERIOR ====================
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);
        topPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220)),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        // Tabs: Pendientes / Historial
        JPanel tabPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        tabPanel.setBackground(Color.WHITE);

        btnPendientes = crearBotonTab("Pendientes", true);
        btnHistorial = crearBotonTab("Historial", false);

        tabPanel.add(btnPendientes);
        tabPanel.add(btnHistorial);

        // Buscador
        JPanel searchPanel = new JPanel(new BorderLayout());
        searchPanel.setBackground(Color.WHITE);
        searchPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        txtBuscar = new JTextField();
        txtBuscar.putClientProperty("JTextField.placeholderText", "Buscar devoluciones...");
        txtBuscar.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        searchPanel.add(txtBuscar, BorderLayout.CENTER);

        topPanel.add(tabPanel, BorderLayout.WEST);
        topPanel.add(searchPanel, BorderLayout.EAST);

        centerPanel.add(topPanel);
        centerPanel.add(Box.createVerticalStrut(10));

        // ==================== TABLA ====================
        String[] columnas = {"Usuario", "Libro", "Fecha Préstamo", "Fecha Esperada", "Multa", "Estado", "Acciones"};
        Object[][] datos = {
            {"Dr. Roberto Martínez\nP000123", "Química Orgánica\n978-0-07-338887-1", "09/10/2025", "23/10/2025", "$4.00", "Vencido", ""},
            {"María González Pérez\nU001234", "Cálculo Diferencial e Integral\n978-0-13-468599-1", "12/10/2025", "26/10/2025", "$0.00", "Activo", ""}
        };

        DefaultTableModel model = new DefaultTableModel(datos, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaDevoluciones = new JTable(model);
        tablaDevoluciones.setRowHeight(45);
        tablaDevoluciones.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tablaDevoluciones.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tablaDevoluciones.getTableHeader().setBackground(new Color(240, 240, 240));
        tablaDevoluciones.setSelectionBackground(new Color(225, 235, 255));
        tablaDevoluciones.setShowGrid(false);

        // Render personalizado
        
        tablaDevoluciones.getColumnModel().getColumn(4).setCellRenderer(new TableRendererDevoluciones()); // Multa
        tablaDevoluciones.getColumnModel().getColumn(5).setCellRenderer(new TableRendererDevoluciones()); // Estado
        tablaDevoluciones.getColumnModel().getColumn(6).setCellRenderer(new TableRendererDevoluciones()); // placeholder
        tablaDevoluciones.getColumnModel().getColumn(6).setCellEditor(new TableEditorDevoluciones()); // 👈 Editor con botón funcional

        JScrollPane scroll = new JScrollPane(tablaDevoluciones);
        scroll.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220)),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        centerPanel.add(scroll);
        add(centerPanel, BorderLayout.CENTER);
    }

    // === Tarjeta estadística ===
    private JPanel crearCard(String titulo, String valor, Color color) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(230, 230, 230)),
                BorderFactory.createEmptyBorder(15, 20, 15, 20)
        ));
        card.setBackground(Color.WHITE);

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblTitulo.setForeground(new Color(100, 100, 100));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblValor = new JLabel(valor);
        lblValor.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblValor.setForeground(color);
        lblValor.setAlignmentX(Component.CENTER_ALIGNMENT);

        card.add(lblTitulo);
        card.add(Box.createVerticalStrut(10));
        card.add(lblValor);
        return card;
    }

    // === Botones de pestañas ===
    private JButton crearBotonTab(String texto, boolean activo) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));

        if (activo) {
            btn.setBackground(new Color(15, 15, 35));
            btn.setForeground(Color.WHITE);
        } else {
            btn.setBackground(new Color(245, 247, 250));
            btn.setForeground(Color.BLACK);
        }

        return btn;
    }
}
