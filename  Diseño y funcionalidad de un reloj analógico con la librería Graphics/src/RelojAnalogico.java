import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;

public class RelojAnalogico extends JPanel {

    public RelojAnalogico() {
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaint(); 
            }
        });
        timer.start(); 
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int ancho = getWidth();
        int alto = getHeight();
        int centroX = ancho / 2;
        int centroY = alto / 2;
        
        int radio = Math.min(centroX, centroY) - 20;

        g2.setColor(Color.WHITE);
        g2.fillOval(centroX - radio, centroY - radio, radio * 2, radio * 2);
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(4));
        g2.drawOval(centroX - radio, centroY - radio, radio * 2, radio * 2);

        g2.setFont(new Font("Arial", Font.BOLD, 24));
        FontMetrics metricas = g2.getFontMetrics();
        int radioNumeros = radio - 35;

        for (int i = 1; i <= 12; i++) {
            double angulo = Math.toRadians((i * 30) - 90);
            
            int x = centroX + (int) (radioNumeros * Math.cos(angulo));
            int y = centroY + (int) (radioNumeros * Math.sin(angulo));
            
            String numero = String.valueOf(i);
            
            int anchoTexto = metricas.stringWidth(numero);
            int altoTexto = metricas.getAscent();
            
            g2.drawString(numero, x - (anchoTexto / 2), y + (altoTexto / 3));
        }

        LocalTime horaActual = LocalTime.now();
        int horas = horaActual.getHour();
        int minutos = horaActual.getMinute();
        int segundos = horaActual.getSecond();

        double anguloHoras = Math.toRadians((horas * 30) + (minutos * 0.5) - 90);
        int longitudHoras = (int) (radio * 0.5); 
        dibujarManecilla(g2, centroX, centroY, anguloHoras, longitudHoras, 10, Color.BLACK);

        double anguloMinutos = Math.toRadians((minutos * 6) - 90);
        int longitudMinutos = (int) (radio * 0.75); 
        dibujarManecilla(g2, centroX, centroY, anguloMinutos, longitudMinutos, 6, Color.DARK_GRAY);

        double anguloSegundos = Math.toRadians((segundos * 6) - 90);
        int longitudSegundos = (int) (radio * 0.85); 
        dibujarManecilla(g2, centroX, centroY, anguloSegundos, longitudSegundos, 10, Color.RED);
        
        g2.setColor(Color.RED);
        g2.fillOval(centroX - 8, centroY - 8, 16, 16);
        g2.setColor(Color.BLACK);
        g2.drawOval(centroX - 8, centroY - 8, 16, 16);
    }

    private void dibujarManecilla(Graphics2D g2, int cx, int cy, double angulo, int longitud, int grosor, Color color) {
        int x = cx + (int) (longitud * Math.cos(angulo));
        int y = cy + (int) (longitud * Math.sin(angulo));

        g2.setColor(color);
        g2.setStroke(new BasicStroke(grosor, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND)); 
        
        g2.drawLine(cx, cy, x, y); 
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame marco = new JFrame("Reloj Analógico");
            marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            marco.setSize(500, 500);
            marco.setLocationRelativeTo(null); 
            
            RelojAnalogico reloj = new RelojAnalogico();
            reloj.setBackground(new Color(220, 230, 240));
            marco.add(reloj);
            
            marco.setVisible(true);
        });
    }
}