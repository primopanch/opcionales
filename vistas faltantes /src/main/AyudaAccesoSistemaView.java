package main;

import javax.swing.*;
import java.awt.*;

public class AyudaAccesoSistemaView extends JPanel {
    public AyudaAccesoSistemaView(VentanaFuncional principal) {
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        JLabel titulo = new JLabel("Centro de Ayuda y Soporte");
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        gbc.gridx = 0; gbc.gridy = 0; gbc.insets = new Insets(0,0,20,0);
        this.add(titulo, gbc);

        JLabel info = new JLabel("<html><body style='width: 300px; text-align: justify;'>" +
            "Si tiene problemas para acceder a su cuenta, asegúrese de:<br><br>" +
            "1. Verificar que su Bloq Mayús esté desactivado.<br>" +
            "2. Usar su correo institucional completo.<br>" +
            "3. Contactar al administrador si el error persiste.</body></html>");
        gbc.gridy = 1;
        this.add(info, gbc);

        JButton btnVolver = new JButton("Entendido, volver");
        btnVolver.addActionListener(e -> principal.router("inicio"));
        gbc.gridy = 2; gbc.insets = new Insets(30,0,0,0);
        this.add(btnVolver, gbc);
    }
}