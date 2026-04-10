import java.awt.*;
import javax.swing.*;

public class JuegoCartasUI extends JFrame {

   
    private JPanel panelTablero, panelControl, panelStats;
    private JButton btnPausa, btnReset;
    private JLabel lblTiempo, lblIntentos, lblMovimientos;
   
    private int contadorMovimientos = 0;
    private int contadorIntentos = 0;
    private String tiempoTranscurrido = "00:00";

    public JuegoCartasUI() {
        
        setTitle("Simulador de Juego de Cartas - Estructura Visual");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 700);
        setLayout(new BorderLayout(10, 10));

        inicializarComponentes();
        
        setVisible(true);
        setLocationRelativeTo(null);
    }

    private void inicializarComponentes() {
      
        panelStats = new JPanel(new GridLayout(1, 3));
        panelStats.setBorder(BorderFactory.createTitledBorder("Estadísticas"));
        
        lblTiempo = new JLabel("Tiempo: " + tiempoTranscurrido, SwingConstants.CENTER);
        lblIntentos = new JLabel("Intentos: " + contadorIntentos, SwingConstants.CENTER);
        lblMovimientos = new JLabel("Movimientos: " + contadorMovimientos, SwingConstants.CENTER);
        
        panelStats.add(lblTiempo);
        panelStats.add(lblIntentos);
        panelStats.add(lblMovimientos);

        panelTablero = new JPanel(new GridLayout(4, 4, 10, 10));
        panelTablero.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

   
        for (int i = 1; i <= 16; i++) {
            JButton carta = new JButton("?");
            carta.setFont(new Font("Arial", Font.BOLD, 20));
            carta.setBackground(Color.LIGHT_GRAY);
            carta.setFocusable(false);
            panelTablero.add(carta);
        }

        panelControl = new JPanel();
        btnPausa = new JButton("Pausar Juego");
        btnReset = new JButton("Reiniciar Partida");
        
        panelControl.add(btnPausa);
        panelControl.add(btnReset);


        add(panelStats, BorderLayout.NORTH);
        add(panelTablero, BorderLayout.CENTER);
        add(panelControl, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        // Ejecución en el hilo de despacho de eventos de Swing
        SwingUtilities.invokeLater(() -> new JuegoCartasUI());
    }
}