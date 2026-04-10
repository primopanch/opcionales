package main;

import java.awt.*;
import javax.swing.*;

public class VentanaFuncional extends JFrame {

    // CORRECCIÓN 1: Se agregaron los paréntesis al constructor
    public VentanaFuncional() {
        this.setSize(1000, 650);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle("Sistema de Gestión - UABCS");
        this.setLayout(new BorderLayout());

        this.menu(); 
        this.router("inicio"); 
        
        this.setVisible(true);
    }

    public void router(String target) {
        this.getContentPane().removeAll();

        JPanel vistaActual; // Declaración

        switch (target) {
            case "login":
                vistaActual = new LoginView(this);
                break;
            case "alta_usuario":
                vistaActual = new AltaUsuarioView(this);
                break;
            case "ayuda_login":
                vistaActual = new AyudaAccesoSistemaView(this);
                break;
            default:
                // Vista de inicio simple
                vistaActual = new JPanel();
                vistaActual.add(new JLabel("Bienvenido al Sistema. Use el menú para navegar."));
                break;
        }

        this.add(vistaActual, BorderLayout.CENTER);
        
        
        this.revalidate();
        this.repaint();
    }

    public void menu() {
        JMenuBar barra = new JMenuBar();
        
        JMenu menuCuenta = new JMenu("Cuenta");
        JMenuItem itemLogin = new JMenuItem("Login");
        itemLogin.addActionListener(e -> router("login"));
        menuCuenta.add(itemLogin);

        JMenu menuUsuarios = new JMenu("Usuarios");
        JMenuItem itemAlta = new JMenuItem("Alta de Usuario");
        itemAlta.addActionListener(e -> router("alta_usuario"));
        menuUsuarios.add(itemAlta);

        JMenu menuAyuda = new JMenu("Ayuda");
        JMenuItem itemAyudaLogin = new JMenuItem("Ayuda Login");
        itemAyudaLogin.addActionListener(e -> router("ayuda_login"));
        menuAyuda.add(itemAyudaLogin);

        this.setJMenuBar(barra);
    }

    public static void main(String[] args) {
        new VentanaFuncional();
    }
}

// Clases de ejemplo (Deben estar en archivos separados según tu actividad)
class LoginView extends JPanel {
    public LoginView(VentanaFuncional principal) {
        setBackground(Color.LIGHT_GRAY);
        add(new JLabel("PANTALLA DE LOGIN"));
        JButton volver = new JButton("Volver");
        volver.addActionListener(e -> principal.router("inicio"));
        add(volver);
    }
}

class AltaUsuarioView extends JPanel {
    public AltaUsuarioView(VentanaFuncional principal) {
        setBackground(Color.CYAN);
        add(new JLabel("PANTALLA DE ALTA"));
    }
}

class AyudaAccesoSistemaView extends JPanel {
    public AyudaAccesoSistemaView(VentanaFuncional principal) {
        add(new JLabel("PANTALLA DE AYUDA"));
    }
}