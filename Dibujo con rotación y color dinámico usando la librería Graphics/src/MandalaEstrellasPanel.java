import java.awt.*;
import java.util.Random;
import javax.swing.*;

public class MandalaEstrellasPanel extends JPanel {

    private Random random = new Random();

    public MandalaEstrellasPanel() {
        setBackground(new Color(240, 240, 240));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int ancho = getWidth();
        int alto = getHeight();

        g2d.translate(ancho / 2.0, alto / 2.0);

        Polygon estrellaBase = crearEstrella(5, 75, 35);
        int totalEstrellas = 30;
        double anguloPorPaso = 2 * Math.PI / totalEstrellas;

        random.setSeed(42);

        for (int i = 0; i < totalEstrellas; i++) {
            g2d.setColor(generarColorOpaco());
            
            g2d.rotate(anguloPorPaso);
            
            g2d.translate(0, -90);
            g2d.fill(estrellaBase);
            g2d.translate(0, 90);
        }
    }

    private Polygon crearEstrella(int puntas, double radioExterior, double radioInterior) {
        Polygon p = new Polygon();
        double anguloInicial = Math.PI / 2;

        for (int i = 0; i < puntas * 2; i++) {
            double r = (i % 2 == 0) ? radioExterior : radioInterior;
            double angulo = anguloInicial + i * Math.PI / puntas;
            int x = (int) (r * Math.cos(angulo));
            int y = (int) (-r * Math.sin(angulo));
            p.addPoint(x, y);
        }
        return p;
    }

    private Color generarColorOpaco() {
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        return new Color(r, g, b); 
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Mandala de Estrellas Opacas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new MandalaEstrellasPanel());
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}