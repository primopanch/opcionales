import java.awt.*;
import java.util.Random;
import javax.swing.*;

public class ComposicionAleatoriaPanel extends JPanel {

    private Random random = new Random();
    private final int CANTIDAD_FIGURAS = 20;

    public ComposicionAleatoriaPanel() {
        setBackground(new Color(245, 245, 245));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        random.setSeed(12345);

        for (int i = 0; i < CANTIDAD_FIGURAS; i++) {
            
            int x = random.nextInt(getWidth());
            int y = random.nextInt(getHeight());
            double escala = 0.5 + random.nextDouble() * 1.5;
            double rotacion = random.nextDouble() * Math.PI * 2;
            int picos = 15 + random.nextInt(15);
            
            g2d.setColor(generarColorOpaco());

            drawFigura(g2d, x, y, escala, rotacion, picos);
        }
    }

    private void drawFigura(Graphics2D g2d, int x, int y, double escala, double rotacion, int picos) {
    
        var oldTransform = g2d.getTransform();

        // Aplicamos transformaciones locales
        g2d.translate(x, y);
        g2d.rotate(rotacion);
        g2d.scale(escala, escala);

        Polygon p = new Polygon();
        for (int i = 0; i < picos * 2; i++) {
            double radio = (i % 2 == 0) ? 40 : 20;
            double angulo = i * Math.PI / picos;
            int px = (int) (radio * Math.cos(angulo));
            int py = (int) (radio * Math.sin(angulo));
            p.addPoint(px, py);
        }

        g2d.fill(p);
        
        
        g2d.setColor(g2d.getColor().darker());
        g2d.draw(p);

        
        g2d.setTransform(oldTransform);
    }

    private Color generarColorOpaco() {
        return new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("G Graphics Library - Demo 11");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new ComposicionAleatoriaPanel());
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}