import java.awt.*;
import javax.swing.*;

public class mickimau extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        

        g.setColor(Color.BLACK);
        g.fillOval(80, 50, 80, 80);  
        g.fillOval(240, 50, 80, 80); 

       
        g.fillOval(100, 80, 200, 180);

        
        Color colorPiel = new Color(255, 224, 189);
        g.setColor(colorPiel);
    
        g.fillOval(115, 110, 170, 140);
        g.setColor(Color.BLACK);
        g.fillOval(160, 175, 15, 20); 
        g.fillOval(225, 175, 15, 20);
        g.fillOval(192, 195, 16, 12);
        g.setColor(Color.WHITE);
        g.drawArc(130, 245, 30, 20, 0, 180); 
        g.drawArc(240, 245, 30, 20, 0, 180); 
    }

    
    public static void main(String[] args) {
        JFrame ventana = new JFrame("Réplica de Personaje - Java Graphics");
        mickimau panel = new mickimau();
        
        ventana.add(panel);
        ventana.setSize(400, 400);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }
}