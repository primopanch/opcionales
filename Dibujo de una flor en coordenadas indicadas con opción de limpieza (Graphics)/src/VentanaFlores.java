import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VentanaFlores extends JFrame {
    
    private PanelLienzo lienzo;
    
    private JTextField campoX;
    private JTextField campoY;

    public VentanaFlores() {
        setTitle("Dibujo Dinámico de Flores");
        setSize(500, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        lienzo = new PanelLienzo();
        lienzo.setBackground(Color.WHITE);
        add(lienzo, BorderLayout.CENTER);

        JPanel panelControles = new JPanel();
        panelControles.setBackground(new Color(200, 210, 255));

        panelControles.add(new JLabel("X-Coordinate:"));
        campoX = new JTextField(4);
        panelControles.add(campoX);

        panelControles.add(new JLabel("Y-Coordinate:"));
        campoY = new JTextField(4);
        panelControles.add(campoY);

        JButton btnDraw = new JButton("Draw");
        JButton btnClear = new JButton("Clear");
        panelControles.add(btnDraw);
        panelControles.add(btnClear);

        add(panelControles, BorderLayout.SOUTH);

        btnDraw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int x = Integer.parseInt(campoX.getText());
                    int y = Integer.parseInt(campoY.getText());
                    
                    Flor nuevaFlor = new Flor(x, y, Color.PINK);
                    lienzo.agregarFlor(nuevaFlor);
                    
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, 
                        "Por favor, ingresa valores numéricos enteros.", 
                        "Error de entrada", 
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnClear.addActionListener(e -> lienzo.limpiarLienzo());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new VentanaFlores().setVisible(true);
        });
    }
}

class Flor {
    private int x;
    private int y;
    private Color colorPetalos;

    public Flor(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.colorPetalos = color;
    }

    public void dibujar(Graphics2D g2) {
        g2.setColor(new Color(34, 139, 34));
        g2.setStroke(new BasicStroke(2));
        g2.drawArc(x - 10, y + 15, 30, 50, 180, 90);

        g2.setColor(colorPetalos);
        int radioPetalo = 34;
        int distanciaAlCentro = 16;
        
        for (int i = 0; i < 5; i++) {
            double angulo = Math.toRadians(i * 72 - 90);
            
            int px = x + (int) (distanciaAlCentro * Math.cos(angulo)) - (radioPetalo / 2);
            int py = y + (int) (distanciaAlCentro * Math.sin(angulo)) - (radioPetalo / 2);
            
            g2.fillOval(px, py, radioPetalo, radioPetalo);
        }

        g2.setColor(Color.YELLOW);
        int radioCentro = 24;
        g2.fillOval(x - (radioCentro / 2), y - (radioCentro / 2), radioCentro, radioCentro);
    }
}

class PanelLienzo extends JPanel {
    
    private ArrayList<Flor> historialFlores;

    public PanelLienzo() {
        historialFlores = new ArrayList<>();
    }

    public void agregarFlor(Flor flor) {
        historialFlores.add(flor);
        repaint();
    }

    public void limpiarLienzo() {
        historialFlores.clear();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (Flor flor : historialFlores) {
            flor.dibujar(g2);
        }
    }
}