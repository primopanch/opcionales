package main;

import javax.swing.*;
import java.awt.*;

public class AltaUsuarioView extends JPanel {
    public AltaUsuarioView(VentanaFuncional principal) {
        this.setLayout(null);
        this.setBackground(new Color(60, 90, 150)); // Color azul oscuro similar a tu imagen

        // Título de la sección
        JLabel titulo = new JLabel("Registro nuevo usuario");
        titulo.setForeground(Color.YELLOW);
        titulo.setFont(new Font("Arial", Font.ITALIC + Font.BOLD, 28));
        titulo.setBounds(300, 30, 400, 40);
        this.add(titulo);

        // Campos del formulario
        agregarCampo("Ingrese tu nombre de usuario", 100);
        agregarCampo("Ingrese su correo electrónico", 180);
        agregarCampo("Escriba una descripción", 260);

        // Botón Volver
        JButton btnVolver = new JButton("Acceder / Volver");
        btnVolver.setBackground(Color.BLACK);
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setOpaque(true);
        btnVolver.setBorderPainted(false);
        btnVolver.setBounds(700, 520, 150, 40);
        btnVolver.addActionListener(e -> principal.router("inicio"));
        this.add(btnVolver);
    }

    private void agregarCampo(String labelText, int yPos) {
        JLabel etiqueta = new JLabel(labelText);
        etiqueta.setForeground(Color.WHITE);
        etiqueta.setBackground(Color.BLACK);
        etiqueta.setOpaque(true);
        etiqueta.setBounds(350, yPos, 300, 20);
        this.add(etiqueta);

        JTextField campo = new JTextField();
        campo.setBounds(350, yPos + 25, 300, 35);
        this.add(campo);
    }
}