package views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import views.components.TableRendererPrestamos;

public class PrestamosView extends JPanel {

    private JTable tablaPrestamos;
    private JTextField txtBuscar;
    private JButton btnNuevo;

    public PrestamosView() {
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

        JLabel lblTitulo = new JLabel("Control de Préstamos");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));

        JLabel lblSubtitulo = new JLabel("Gestión de préstamos de libros a usuarios");
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

        // --- Tarjetas de resumen ---
        JPanel cardsPanel = new JPanel(new GridLayout(1, 3, 15, 0));
        cardsPanel.setBackground(getBackground());
        cardsPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));

        cardsPanel.add(crearCard("Préstamos Activos", "3", new Color(40, 167, 69)));
        cardsPanel.add(crearCard("Préstamos Vencidos", "1", new Color(220, 53, 69)));
        cardsPanel.add(crearCard("Total Préstamos", "4", new Color(33, 37, 41)));

        centerPanel.add(cardsPanel);

        // --- Panel superior (buscador + botón) ---
        JPanel searchPanel = new JPanel(new BorderLayout(10, 0));
        searchPanel.setBackground(Color.WHITE);
        searchPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220)),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        txtBuscar = new JTextField();
        txtBuscar.putClientProperty("JTextField.placeholderText", "Buscar por usuario o libro...");
        txtBuscar.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        btnNuevo = new JButton("➕ Nuevo Préstamo");
        btnNuevo.setBackground(new Color(15, 15, 35));
        btnNuevo.setForeground(Color.WHITE);
        btnNuevo.setFocusPainted(false);
        btnNuevo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnNuevo.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));

        searchPanel.add(txtBuscar, BorderLayout.CENTER);
        searchPanel.add(btnNuevo, BorderLayout.EAST);

        centerPanel.add(searchPanel);
        centerPanel.add(Box.createVerticalStrut(10));

        // ==================== TABLA ====================
        String[] columnas = {"Usuario", "Libro", "Fecha Préstamo", "Fecha Devolución", "Estado", "Días Restantes"};
        Object[][] datos = {
                {"María González Pérez\nU001234", "Cálculo Diferencial e Integral\n978-0-13-468599-1", "12/10/2025", "26/10/2025", "Activo", "7"},
                {"Juan Carlos Ramírez\nU001235", "Física Universitaria Vol. 1\n978-0-13-468678-3", "14/10/2025", "28/10/2025", "Activo", "9"},
                {"Dr. Roberto Martínez\nP000123", "Química Orgánica\n978-0-07-338887-1", "09/10/2025", "23/10/2025", "Vencido", "-4"},
                {"Ana Patricia Torres\nU001236", "Programación en Java\n978-0-13-468599-8", "17/10/2025", "31/10/2025", "Activo", "12"}
        };

        DefaultTableModel model = new DefaultTableModel(datos, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaPrestamos = new JTable(model);
        tablaPrestamos.setRowHeight(45);
        tablaPrestamos.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tablaPrestamos.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tablaPrestamos.getTableHeader().setBackground(new Color(240, 240, 240));
        tablaPrestamos.setSelectionBackground(new Color(225, 235, 255));
        tablaPrestamos.setShowGrid(false);

        // Render personalizado
        tablaPrestamos.getColumnModel().getColumn(4).setCellRenderer(new TableRendererPrestamos()); // Estado
        tablaPrestamos.getColumnModel().getColumn(5).setCellRenderer(new TableRendererPrestamos()); // Días restantes

        JScrollPane scroll = new JScrollPane(tablaPrestamos);
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
}
