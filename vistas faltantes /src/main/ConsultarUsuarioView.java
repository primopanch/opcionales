package main;

import javax.swing.*;
import java.awt.*;

public class ConsultarUsuarioView extends JPanel {
    public ConsultarUsuarioView(VentanaFuncional principal) {
        this.setLayout(new BorderLayout(20, 20));
        this.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        JLabel titulo = new JLabel("Consulta de Usuarios Registrados", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        this.add(titulo, BorderLayout.NORTH);

        // Definición de la tabla
        String[] columnas = {"Num Control", "Apellidos", "Nombres", "Acciones"};
        Object[][] datos = {
            {"10191823", "Zuma", "Abrim", "Editar"},
            {"91923844", "Ari", "Cruz", "Editar"},
            {"91284751", "Rodri", "Ferna", "Editar"},
            {"01928374", "Abaro", "Angel", "Editar"}
        };
        
        JTable tabla = new JTable(datos, columnas);
        JScrollPane scroll = new JScrollPane(tabla);
        this.add(scroll, BorderLayout.CENTER);

        JButton btnVolver = new JButton("Regresar al Menú");
        btnVolver.addActionListener(e -> principal.router("inicio"));
        this.add(btnVolver, BorderLayout.SOUTH);
    }
}