package com.yjj.library.view;

import com.yjj.library.view.components.TableRendererLibros;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class LibrosView extends JPanel {

    private JTable tablaLibros;
    private JTextField txtBuscar;
    private JButton btnNuevo;

    public LibrosView() {
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

        JLabel lblTitulo = new JLabel("Catálogo de Libros");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));

        JLabel lblSubtitulo = new JLabel("Gestión de libros y materiales bibliográficos");
        lblSubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        lblSubtitulo.setForeground(new Color(100, 100, 100));

        headerPanel.add(lblTitulo);
        headerPanel.add(Box.createVerticalStrut(5));
        headerPanel.add(lblSubtitulo);
        add(headerPanel, BorderLayout.NORTH);

        // ==================== PANEL CENTRAL ====================
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(getBackground());
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        // --- Panel superior (buscador + botón) ---
        JPanel searchPanel = new JPanel(new BorderLayout(10, 0));
        searchPanel.setBackground(Color.WHITE);
        searchPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220)),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        txtBuscar = new JTextField();
        txtBuscar.putClientProperty("JTextField.placeholderText", "Buscar por título, autor o ISBN...");
        txtBuscar.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        btnNuevo = new JButton("➕ Nuevo Libro");
        btnNuevo.setBackground(new Color(15, 15, 35));
        btnNuevo.setForeground(Color.WHITE);
        btnNuevo.setFocusPainted(false);
        btnNuevo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnNuevo.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));

        searchPanel.add(txtBuscar, BorderLayout.CENTER);
        searchPanel.add(btnNuevo, BorderLayout.EAST);

        centerPanel.add(searchPanel, BorderLayout.NORTH);
        centerPanel.add(Box.createVerticalStrut(10), BorderLayout.CENTER);

        // ==================== TABLA ====================
        String[] columnas = {"ISBN", "Título", "Autor", "Categoría", "Ubicación", "Disponibles", "Acciones"};
        Object[][] datos = {
                {"978-0-13-468599-1", "Cálculo Diferencial e Integral\nCengage Learning (2020)", "James Stewart", "Matemáticas", "Estante A-12", "12/15", ""},
                {"978-0-13-468678-3", "Física Universitaria Vol. 1\nPearson (2018)", "Young & Freedman", "Física", "Estante B-05", "15/20", ""},
                {"978-0-07-338887-1", "Química Orgánica\nMcGraw-Hill (2019)", "Morrison & Boyd", "Química", "Estante C-08", "8/10", ""},
                {"978-0-13-468599-8", "Programación en Java\nPearson (2021)", "Deitel & Deitel", "Informática", "Estante D-15", "18/25", ""},
                {"978-0-13-394620-5", "Base de Datos: Diseño y Programación\nMcGraw-Hill (2020)", "Silberschatz", "Informática", "Estante D-18", "0/12", ""}
        };

        DefaultTableModel model = new DefaultTableModel(datos, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaLibros = new JTable(model);
        tablaLibros.setRowHeight(45);
        tablaLibros.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tablaLibros.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tablaLibros.getTableHeader().setBackground(new Color(240, 240, 240));
        tablaLibros.setSelectionBackground(new Color(225, 235, 255));
        tablaLibros.setShowGrid(false);

        // Render personalizado
        tablaLibros.getColumnModel().getColumn(3).setCellRenderer(new TableRendererLibros()); // Categoría
        tablaLibros.getColumnModel().getColumn(5).setCellRenderer(new TableRendererLibros()); // Disponibles
        tablaLibros.getColumnModel().getColumn(6).setCellRenderer(new TableRendererLibros()); // Acciones

        JScrollPane scroll = new JScrollPane(tablaLibros);
        scroll.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220)),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        centerPanel.add(scroll, BorderLayout.CENTER);
        add(centerPanel, BorderLayout.CENTER);
    }
}
