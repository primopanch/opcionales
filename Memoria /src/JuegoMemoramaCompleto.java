import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class JuegoMemoramaCompleto extends JFrame {

    private JPanel panelTablero, panelStats;
    private JButton btnReset;
    private JLabel lblMovimientos, lblParejasEncontradas;
    
    private int contadorMovimientos = 0;
    private int contadorParejasEncontradas = 0;
    private final int TOTAL_PAREJAS = 8;

    private JButton primeraCartaAbierta = null;
    private JButton segundaCartaAbierta = null;
    
    private Timer temporizadorOcultar;

    public JuegoMemoramaCompleto() {
        setTitle("Juego de Memoria");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 800);
        setLayout(new BorderLayout(15, 15));

        temporizadorOcultar = new Timer(1200, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ocultarCartasIncorrectas();
            }
        });
        temporizadorOcultar.setRepeats(false);

        inicializarComponentes();
        reiniciarJuego(false);

        setVisible(true);
        setLocationRelativeTo(null);
    }
    
    private void inicializarComponentes() {
        panelStats = new JPanel(new GridLayout(1, 3, 10, 0));
        panelStats.setBorder(BorderFactory.createEmptyBorder(10, 20, 0, 20));
        
        lblMovimientos = new JLabel("Movimientos: 0", SwingConstants.CENTER);
        lblMovimientos.setFont(new Font("Arial", Font.BOLD, 16));
        
        lblParejasEncontradas = new JLabel("Parejas: 0 / " + TOTAL_PAREJAS, SwingConstants.CENTER);
        lblParejasEncontradas.setFont(new Font("Arial", Font.BOLD, 16));

        btnReset = new JButton("Reiniciar Partida");
        btnReset.setFont(new Font("Arial", Font.BOLD, 14));
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reiniciarJuego(true);
            }
        });
        
        panelStats.add(lblMovimientos);
        panelStats.add(lblParejasEncontradas);
        panelStats.add(btnReset);

        panelTablero = new JPanel(new GridLayout(4, 4, 15, 15));
        panelTablero.setBorder(BorderFactory.createEmptyBorder(15, 20, 20, 20));

        CardClickListener listener = new CardClickListener();
        for (int i = 0; i < 16; i++) {
            JButton carta = new JButton();
            carta.setBackground(Color.DARK_GRAY);
            carta.setFocusable(false);
            carta.setBorder(new LineBorder(Color.BLACK, 1));
            carta.addActionListener(listener);
            panelTablero.add(carta);
        }

        add(panelStats, BorderLayout.NORTH);
        add(panelTablero, BorderLayout.CENTER);
    }

    private List<String> prepararYBarajarCartas() {
        String[] valores = {
            "src/gatowe.jpg", "src/mclovin.jpg", "src/nigabart.jpg", "src/si.jpg", 
            "src/stop.jpg", "src/yo.jpg", "src/gatowe.jpg", "src/mclovin.jpg"
        };
        List<String> listaCartas = new ArrayList<>();
        for (String valor : valores) {
            listaCartas.add(valor);
            listaCartas.add(valor);
        }
        Collections.shuffle(listaCartas);
        return listaCartas;
    }

    private class CardClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (temporizadorOcultar.isRunning()) return;

            JButton botonPresionado = (JButton) e.getSource();
            String valorOculto = (String) botonPresionado.getClientProperty("valor");

            if (!botonPresionado.isEnabled() || botonPresionado.getIcon() != null) {
                return;
            }

            revelarCarta(botonPresionado, valorOculto);

            if (primeraCartaAbierta == null) {
                primeraCartaAbierta = botonPresionado;
            } else if (segundaCartaAbierta == null && botonPresionado != primeraCartaAbierta) {
                segundaCartaAbierta = botonPresionado;
                
                contadorMovimientos++;
                actualizarEstadisticas();

                String valor1 = (String) primeraCartaAbierta.getClientProperty("valor");
                String valor2 = (String) segundaCartaAbierta.getClientProperty("valor");

                if (valor1.equals(valor2)) {
                    manejarCoincidencia();
                } else {
                    manejarNoCoincidencia();
                }
            }
        }
    }

    private void revelarCarta(JButton boton, String ruta) {
        ImageIcon iconoOriginal = new ImageIcon(ruta);
        Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        boton.setIcon(new ImageIcon(imagenEscalada));
        boton.setBackground(Color.LIGHT_GRAY);
    }

    private void ocultarCarta(JButton boton) {
        boton.setIcon(null);
        boton.setBackground(Color.DARK_GRAY);
    }

    private void manejarCoincidencia() {
        primeraCartaAbierta.setEnabled(false);
        segundaCartaAbierta.setEnabled(false);
        primeraCartaAbierta.setBackground(new Color(144, 238, 144));
        segundaCartaAbierta.setBackground(new Color(144, 238, 144));
        
        contadorParejasEncontradas++;
        actualizarEstadisticas();
        
        resetTurno();
        
        checkFinDeJuego();
    }

    private void manejarNoCoincidencia() {
        temporizadorOcultar.start();
    }

    private void ocultarCartasIncorrectas() {
        ocultarCarta(primeraCartaAbierta);
        ocultarCarta(segundaCartaAbierta);
        resetTurno();
    }

    private void resetTurno() {
        primeraCartaAbierta = null;
        segundaCartaAbierta = null;
    }

    private void actualizarEstadisticas() {
        lblMovimientos.setText("Movimientos: " + contadorMovimientos);
        lblParejasEncontradas.setText("Parejas: " + contadorParejasEncontradas + " / " + TOTAL_PAREJAS);
    }

    private void checkFinDeJuego() {
        if (contadorParejasEncontradas == TOTAL_PAREJAS) {
            JOptionPane.showMessageDialog(this, 
                "¡Felicidades, has ganado!\nCompletado en " + contadorMovimientos + " movimientos.", 
                "Juego Terminado", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void reiniciarJuego(boolean mostrarConfirmacion) {
        if (mostrarConfirmacion) {
            int respuesta = JOptionPane.showConfirmDialog(this, 
                "¿Estás seguro de que quieres reiniciar la partida actual?", 
                "Confirmar Reinicio", JOptionPane.YES_NO_OPTION);
            if (respuesta != JOptionPane.YES_OPTION) {
                return;
            }
        }

        if (temporizadorOcultar.isRunning()) {
            temporizadorOcultar.stop();
        }

        contadorMovimientos = 0;
        contadorParejasEncontradas = 0;
        resetTurno();
        actualizarEstadisticas();

        List<String> cartasBarajadas = prepararYBarajarCartas();

        for (int i = 0; i < 16; i++) {
            JButton carta = (JButton) panelTablero.getComponent(i);
            ocultarCarta(carta);
            carta.setEnabled(true);
            carta.putClientProperty("valor", cartasBarajadas.get(i));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new JuegoMemoramaCompleto());
    }
}