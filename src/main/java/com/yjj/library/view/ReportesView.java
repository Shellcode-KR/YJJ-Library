package views;

import javax.swing.*;
import java.awt.*;
import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.*;
import org.jfree.chart.renderer.category.*;
import org.jfree.chart.renderer.xy.*;
import org.jfree.chart.axis.*;

public class ReportesView extends JPanel {

    public ReportesView() {
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

        JLabel lblTitulo = new JLabel("Reportes y Estadísticas");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));

        JLabel lblSubtitulo = new JLabel("Análisis de datos y generación de reportes");
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
        JPanel cardsPanel = new JPanel(new GridLayout(1, 4, 15, 0));
        cardsPanel.setBackground(getBackground());
        cardsPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));

        cardsPanel.add(crearCard("Total Préstamos (Oct)", "234", "+12% vs mes anterior", new Color(40, 167, 69)));
        cardsPanel.add(crearCard("Usuarios Activos", "1,248", "+8% vs mes anterior", new Color(40, 167, 69)));
        cardsPanel.add(crearCard("Libros en Circulación", "189", "De 3,567 disponibles", new Color(100, 100, 100)));
        cardsPanel.add(crearCard("Multas Cobradas", "$456", "-5% vs mes anterior", new Color(220, 53, 69)));

        centerPanel.add(cardsPanel);

        // ==================== FILA 1 DE GRÁFICOS ====================
        JPanel row1 = new JPanel(new GridLayout(1, 2, 15, 0));
        row1.setBackground(getBackground());

        row1.add(crearPanelConTitulo("Préstamos y Devoluciones", crearGraficoBarras()));
        row1.add(crearPanelConTitulo("Libros por Categoría", crearGraficoPastel()));

        centerPanel.add(row1);
        centerPanel.add(Box.createVerticalStrut(15));

        // ==================== FILA 2 DE GRÁFICOS ====================
        JPanel row2 = new JPanel(new GridLayout(1, 2, 15, 0));
        row2.setBackground(getBackground());

        row2.add(crearPanelConTitulo("Tendencia de Préstamos (Última Semana)", crearGraficoLinea()));
        row2.add(crearPanelConTitulo("Top 5 Libros Más Prestados", crearGraficoHorizontal()));

        centerPanel.add(row2);

        add(centerPanel, BorderLayout.CENTER);
    }

    // ==================== TARJETAS RESUMEN ====================
    private JPanel crearCard(String titulo, String valor, String subtexto, Color color) {
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
        lblValor.setForeground(Color.BLACK);
        lblValor.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblSub = new JLabel(subtexto);
        lblSub.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblSub.setForeground(color);
        lblSub.setAlignmentX(Component.CENTER_ALIGNMENT);

        card.add(lblTitulo);
        card.add(Box.createVerticalStrut(10));
        card.add(lblValor);
        card.add(Box.createVerticalStrut(5));
        card.add(lblSub);
        return card;
    }

    // ==================== CONTENEDOR DE GRÁFICOS ====================
    private JPanel crearPanelConTitulo(String titulo, JComponent chartPanel) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(230, 230, 230)),
                BorderFactory.createEmptyBorder(10, 15, 15, 15)
        ));

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        panel.add(lblTitulo, BorderLayout.NORTH);
        panel.add(chartPanel, BorderLayout.CENTER);
        return panel;
    }

    // ==================== GRÁFICO DE BARRAS ====================
    private JComponent crearGraficoBarras() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String[] meses = {"Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Sep", "Oct"};
        int[] prestamos = {130, 170, 185, 160, 190, 240, 200, 180, 230, 200};
        int[] devoluciones = {125, 165, 180, 155, 185, 230, 190, 175, 220, 190};

        for (int i = 0; i < meses.length; i++) {
            dataset.addValue(prestamos[i], "Préstamos", meses[i]);
            dataset.addValue(devoluciones[i], "Devoluciones", meses[i]);
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "", "Mes", "Cantidad", dataset,
                PlotOrientation.VERTICAL, true, false, false);

        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setRangeGridlinePaint(new Color(200, 200, 200));

        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, new Color(102, 153, 255)); // Azul
        renderer.setSeriesPaint(1, new Color(80, 200, 120));  // Verde

        return new ChartPanel(chart);
    }

    // ==================== GRÁFICO DE PASTEL ====================
    private JComponent crearGraficoPastel() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Informática", 22);
        dataset.setValue("Matemáticas", 19);
        dataset.setValue("Física", 16);
        dataset.setValue("Ingeniería", 17);
        dataset.setValue("Literatura", 12);
        dataset.setValue("Química", 14);

        JFreeChart chart = ChartFactory.createPieChart("", dataset, false, false, false);
        chart.setBackgroundPaint(Color.WHITE);
        chart.getPlot().setBackgroundPaint(Color.WHITE);

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setSectionPaint("Informática", new Color(255, 190, 60));
        plot.setSectionPaint("Matemáticas", new Color(90, 110, 255));
        plot.setSectionPaint("Física", new Color(130, 120, 255));
        plot.setSectionPaint("Ingeniería", new Color(100, 210, 130));
        plot.setSectionPaint("Literatura", new Color(80, 150, 255));
        plot.setSectionPaint("Química", new Color(240, 90, 140));

        return new ChartPanel(chart);
    }

    // ==================== GRÁFICO DE LÍNEA ====================
    private JComponent crearGraficoLinea() {
        XYSeries series = new XYSeries("Préstamos");
        series.add(15, 20);
        series.add(16, 28);
        series.add(17, 32);
        series.add(18, 25);
        series.add(19, 35);
        series.add(20, 29);

        XYSeriesCollection dataset = new XYSeriesCollection(series);

        JFreeChart chart = ChartFactory.createXYLineChart(
                "", "Fecha", "Cantidad", dataset,
                PlotOrientation.VERTICAL, true, false, false);

        XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setRangeGridlinePaint(new Color(200, 200, 200));

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, new Color(100, 120, 255));
        renderer.setSeriesShapesVisible(0, true);
        plot.setRenderer(renderer);

        NumberAxis domain = (NumberAxis) plot.getDomainAxis();
        domain.setAutoRangeIncludesZero(false);

        return new ChartPanel(chart);
    }

    // ==================== GRÁFICO DE BARRAS HORIZONTALES ====================
    private JComponent crearGraficoHorizontal() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(45, "Libros", "Programación en Java");
        dataset.addValue(38, "Libros", "Cálculo Diferencial e Integral");
        dataset.addValue(32, "Libros", "Física Universitaria");
        dataset.addValue(28, "Libros", "Base de Datos");
        dataset.addValue(25, "Libros", "Química Orgánica");

        JFreeChart chart = ChartFactory.createBarChart(
                "", "Libro", "Préstamos", dataset,
                PlotOrientation.HORIZONTAL, false, false, false);

        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setRangeGridlinePaint(new Color(200, 200, 200));

        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, new Color(100, 110, 255));

        return new ChartPanel(chart);
    }
}
