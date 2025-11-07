package com.yjj.library.view;

import com.yjj.library.view.components.TableRenderer;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UsuariosView extends JPanel {

    private JTable tablaUsuarios;
    private JTextField txtBuscar;
    private JButton btnNuevo;

    public UsuariosView() {
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

        JLabel lblTitulo = new JLabel("Gestión de Usuarios");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));

        JLabel lblSubtitulo = new JLabel("Administración de estudiantes, profesores y personal");
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

        // ---------- Panel de búsqueda y botón ----------
        JPanel searchPanel = new JPanel(new BorderLayout(10, 0));
        searchPanel.setBackground(Color.WHITE);
        searchPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220)),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        // Campo de búsqueda
        txtBuscar = new JTextField();
        txtBuscar.putClientProperty("JTextField.placeholderText", "Buscar usuarios...");
        txtBuscar.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // Botón nuevo usuario
        btnNuevo = new JButton("➕ Nuevo Usuario");
        btnNuevo.setBackground(new Color(15, 15, 35));
        btnNuevo.setForeground(Color.WHITE);
        btnNuevo.setFocusPainted(false);
        btnNuevo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnNuevo.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        btnNuevo.addActionListener(e -> onNuevoUsuario());

        searchPanel.add(txtBuscar, BorderLayout.CENTER);
        searchPanel.add(btnNuevo, BorderLayout.EAST);

        centerPanel.add(searchPanel, BorderLayout.NORTH);
        centerPanel.add(Box.createVerticalStrut(10), BorderLayout.CENTER);

        // ==================== TABLA DE USUARIOS ====================
        String[] columnas = {"Código", "Nombre", "Email", "Tipo", "Carrera", "Estado", "Acciones"};
        Object[][] datos = {
            {"U001234", "María González Pérez", "maria.gonzalez@universidad.edu", "Estudiante", "Ingeniería de Sistemas", "Activo", "✏️ 🗑️"},
            {"U001235", "Juan Carlos Ramírez", "juan.ramirez@universidad.edu", "Estudiante", "Ingeniería Industrial", "Activo", "✏️ 🗑️"},
            {"P000123", "Dr. Roberto Martínez", "r.martinez@universidad.edu", "Profesor", "Facultad de Ciencias", "Activo", "✏️ 🗑️"},
            {"U001236", "Ana Patricia Torres", "ana.torres@universidad.edu", "Estudiante", "Administración", "Inactivo", "✏️ 🗑️"}
        };

        DefaultTableModel model = new DefaultTableModel(datos, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // desactivar edición
            }
        };

        tablaUsuarios = new JTable(model);
        tablaUsuarios.setRowHeight(40);
        tablaUsuarios.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tablaUsuarios.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tablaUsuarios.getTableHeader().setBackground(new Color(240, 240, 240));
        tablaUsuarios.setSelectionBackground(new Color(225, 235, 255));
        tablaUsuarios.setShowGrid(false);

// 🔹 Aplicar render personalizado
        tablaUsuarios.getColumnModel().getColumn(5).setCellRenderer(new TableRenderer()); // Estado
        tablaUsuarios.getColumnModel().getColumn(6).setCellRenderer(new TableRenderer()); // Acciones

        // ScrollPane estilizado
        JScrollPane scroll = new JScrollPane(tablaUsuarios);
        scroll.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220)),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        // Click en fila
        tablaUsuarios.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int fila = tablaUsuarios.getSelectedRow();
                    if (fila != -1) {
                        String nombre = tablaUsuarios.getValueAt(fila, 1).toString();
                        JOptionPane.showMessageDialog(null, "Abrir detalles de: " + nombre);
                    }
                }
            }
        });

        centerPanel.add(scroll, BorderLayout.CENTER);
        add(centerPanel, BorderLayout.CENTER);
    }

    // ==================== ACCIÓN NUEVO USUARIO ====================
    private void onNuevoUsuario() {
        JOptionPane.showMessageDialog(this, "Formulario para crear un nuevo usuario (pendiente)");
    }
}
