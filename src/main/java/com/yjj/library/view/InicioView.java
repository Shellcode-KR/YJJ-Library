package com.yjj.library.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class InicioView extends JPanel {

    public InicioView() {
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        setBackground(new Color(245, 247, 250)); // gris suave

        // --- Encabezado ---
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(getBackground());
        JLabel lblTitulo = new JLabel("Bienvenido al Sistema de Gestión Bibliotecaria");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        JLabel lblSubtitulo = new JLabel("Panel de control principal - Universidad");
        lblSubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblSubtitulo.setForeground(new Color(100, 100, 100));

        JPanel titleBox = new JPanel();
        titleBox.setLayout(new BoxLayout(titleBox, BoxLayout.Y_AXIS));
        titleBox.setBackground(getBackground());
        titleBox.add(lblTitulo);
        titleBox.add(lblSubtitulo);
        headerPanel.add(titleBox, BorderLayout.NORTH);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        add(headerPanel, BorderLayout.NORTH);

        // --- Contenedor principal ---
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(getBackground());

        // Tarjetas superiores
        JPanel cardsPanel = new JPanel(new GridLayout(1, 4, 15, 0));
        cardsPanel.setBackground(getBackground());
        cardsPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        cardsPanel.add(crearCard("Total Usuarios", "1,248", new Color(70, 130, 255), "👥"));
        cardsPanel.add(crearCard("Total Libros", "3,567", new Color(60, 200, 100), "📘"));
        cardsPanel.add(crearCard("Préstamos Activos", "234", new Color(255, 140, 0), "📅"));
        cardsPanel.add(crearCard("Devoluciones Hoy", "45", new Color(150, 90, 255), "📈"));

        // Panel inferior (2 columnas)
        JPanel bottomPanel = new JPanel(new GridLayout(1, 2, 15, 0));
        bottomPanel.setBackground(getBackground());
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        bottomPanel.add(crearActividadRecientePanel());
        bottomPanel.add(crearPrestamosPorVencerPanel());

        mainPanel.add(cardsPanel);
        mainPanel.add(bottomPanel);

        add(mainPanel, BorderLayout.CENTER);
    }

    // ------------------ COMPONENTES AUXILIARES ------------------

    private JPanel crearCard(String titulo, String valor, Color color, String iconoTexto) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 220)),
            BorderFactory.createEmptyBorder(15, 20, 15, 20)
        ));
        card.setBackground(Color.WHITE);

        JLabel lblIcon = new JLabel(iconoTexto);
        lblIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 28));
        lblIcon.setForeground(color);
        lblIcon.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblTitulo.setForeground(new Color(90, 90, 90));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblValor = new JLabel(valor);
        lblValor.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblValor.setForeground(Color.BLACK);
        lblValor.setAlignmentX(Component.CENTER_ALIGNMENT);

        card.add(lblTitulo);
        card.add(Box.createVerticalStrut(10));
        card.add(lblIcon);
        card.add(Box.createVerticalStrut(10));
        card.add(lblValor);
        return card;
    }

    private JPanel crearActividadRecientePanel() {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBackground(Color.WHITE);
        p.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 220)),
            BorderFactory.createEmptyBorder(15, 20, 15, 20)
        ));
        JLabel titulo = new JLabel("Actividad Reciente");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        titulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        p.add(titulo);
        p.add(Box.createVerticalStrut(15));
        p.add(crearLineaActividad("Nuevo préstamo", "María González", "Cálculo Diferencial", "Hace 5 min"));
        p.add(crearLineaActividad("Devolución", "Juan Pérez", "Física Cuántica", "Hace 15 min"));
        p.add(crearLineaActividad("Nuevo usuario", "Ana Martínez", "Registro completado", "Hace 30 min"));
        p.add(crearLineaActividad("Préstamo vencido", "Carlos Ruiz", "Química Orgánica", "Hace 1 hora"));
        return p;
    }

    private JPanel crearLineaActividad(String tipo, String usuario, String detalle, String tiempo) {
        JPanel linea = new JPanel();
        linea.setLayout(new BoxLayout(linea, BoxLayout.Y_AXIS));
        linea.setBackground(Color.WHITE);
        JLabel lbl = new JLabel("<html><b style='color:#4a56e2'>" + tipo + "</b> - " + usuario + "<br>" + detalle + "</html>");
        JLabel lblTiempo = new JLabel(tiempo);
        lblTiempo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblTiempo.setForeground(Color.GRAY);
        linea.add(lbl);
        linea.add(lblTiempo);
        linea.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        return linea;
    }

    private JPanel crearPrestamosPorVencerPanel() {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBackground(Color.WHITE);
        p.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 220)),
            BorderFactory.createEmptyBorder(15, 20, 15, 20)
        ));
        JLabel titulo = new JLabel("Préstamos por Vencer");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        p.add(titulo);
        p.add(Box.createVerticalStrut(15));
        p.add(crearLineaVencimiento("Pedro Sánchez", "Álgebra Lineal", "23 Oct 2025", "3 días"));
        p.add(crearLineaVencimiento("Laura Jiménez", "Programación en Java", "22 Oct 2025", "2 días"));
        p.add(crearLineaVencimiento("Roberto Díaz", "Base de Datos", "21 Oct 2025", "1 día"));
        p.add(crearLineaVencimiento("Carmen López", "Redes de Computadoras", "20 Oct 2025", "Hoy"));
        return p;
    }

    private JPanel crearLineaVencimiento(String nombre, String materia, String fecha, String etiqueta) {
        JPanel linea = new JPanel(new BorderLayout());
        linea.setBackground(Color.WHITE);

        JLabel lblInfo = new JLabel("<html><b>" + nombre + "</b><br>" + materia + "<br><span style='color:gray'>Vence: " + fecha + "</span></html>");
        JLabel lblTag = new JLabel(etiqueta);
        lblTag.setOpaque(true);
        lblTag.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblTag.setForeground(Color.DARK_GRAY);
        lblTag.setHorizontalAlignment(SwingConstants.CENTER);
        lblTag.setBorder(BorderFactory.createEmptyBorder(3, 8, 3, 8));

        switch (etiqueta) {
            case "Hoy":
                lblTag.setBackground(new Color(255, 200, 200));
                break;
            case "1 día":
                lblTag.setBackground(new Color(255, 170, 170));
                break;
            default:
                lblTag.setBackground(new Color(255, 240, 180));
        }

        linea.add(lblInfo, BorderLayout.CENTER);
        linea.add(lblTag, BorderLayout.EAST);
        linea.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        return linea;
    }
}
