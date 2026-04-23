import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class PaintApp extends JFrame {

    private LienzoPanel lienzo;
    private int grosorActual = 1;

    public PaintApp() {
        setTitle("Paint App - Java");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        lienzo = new LienzoPanel();
        add(lienzo, BorderLayout.CENTER);

        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBorder(BorderFactory.createEmptyBorder(20, 15, 20, 15));
        sidebar.setBackground(new Color(235, 235, 235));
        sidebar.setPreferredSize(new Dimension(140, getHeight()));

        Dimension dimBotones = new Dimension(100, 100);
        Font fuenteSimbolos = new Font("Arial", Font.PLAIN, 40);
        Font fuenteTexto = new Font("Arial", Font.PLAIN, 18);

        JButton btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnLimpiar.setPreferredSize(dimBotones);
        btnLimpiar.setMaximumSize(dimBotones);
        btnLimpiar.setFont(new Font("Arial", Font.BOLD, 16));
        btnLimpiar.setFocusPainted(false);
        btnLimpiar.addActionListener(e -> lienzo.limpiar());

        JButton btnMenos = new JButton("-");
        btnMenos.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnMenos.setPreferredSize(dimBotones);
        btnMenos.setMaximumSize(dimBotones);
        btnMenos.setFont(fuenteSimbolos);
        btnMenos.setFocusPainted(false);

        JLabel lblGrosor = new JLabel(String.valueOf(grosorActual));
        lblGrosor.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblGrosor.setFont(fuenteTexto);

        JButton btnMas = new JButton("+");
        btnMas.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnMas.setPreferredSize(dimBotones);
        btnMas.setMaximumSize(dimBotones);
        btnMas.setFont(fuenteSimbolos);
        btnMas.setFocusPainted(false);

        btnMenos.addActionListener(e -> {
            if (grosorActual > 1) {
                grosorActual--;
                lblGrosor.setText(String.valueOf(grosorActual));
                lienzo.setGrosor(grosorActual);
            }
        });

        btnMas.addActionListener(e -> {
            if (grosorActual < 50) {
                grosorActual++;
                lblGrosor.setText(String.valueOf(grosorActual));
                lienzo.setGrosor(grosorActual);
            }
        });

        sidebar.add(btnLimpiar);
        sidebar.add(Box.createRigidArea(new Dimension(0, 20)));
        sidebar.add(btnMenos);
        sidebar.add(Box.createRigidArea(new Dimension(0, 40)));
        sidebar.add(lblGrosor);
        sidebar.add(Box.createRigidArea(new Dimension(0, 40)));
        sidebar.add(btnMas);

        add(sidebar, BorderLayout.WEST);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PaintApp().setVisible(true));
    }
}

class LienzoPanel extends JPanel {
    private BufferedImage imagenCanvas;
    private Graphics2D g2d;
    private Point puntoAnterior;
    private int grosor = 1;

    public LienzoPanel() {
        setBackground(Color.WHITE);
        setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));

        MouseAdapter mouseHandler = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                puntoAnterior = e.getPoint();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if (puntoAnterior != null && g2d != null) {
                    Point puntoActual = e.getPoint();
                    g2d.setStroke(new BasicStroke(grosor, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                    g2d.setPaint(Color.BLACK);
                    g2d.drawLine(puntoAnterior.x, puntoAnterior.y, puntoActual.x, puntoActual.y);
                    puntoAnterior = puntoActual;
                    repaint();
                }
            }
        };

        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagenCanvas == null) {
            imagenCanvas = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
            g2d = imagenCanvas.createGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            limpiar();
        }
        g.drawImage(imagenCanvas, 0, 0, null);
    }

    public void limpiar() {
        if (g2d != null) {
            g2d.setPaint(Color.WHITE);
            g2d.fillRect(0, 0, getWidth(), getHeight());
            g2d.setPaint(Color.BLACK);
            repaint();
        }
    }

    public void setGrosor(int nuevoGrosor) {
        this.grosor = nuevoGrosor;
    }
}