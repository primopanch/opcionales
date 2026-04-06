import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class UpdateARCSControlFile extends JFrame {

    // Declaración de componentes como variables de instancia para acceder a ellos al guardar
    private JSpinner yearSpinner;
    private JTextField txtBatch1, txtBatch2, txtCO;
    private JTextField txtAccount1, txtAccount2;
    private JComboBox<String> cbSource, cbCircumstance;
    private JTextField txtCMI, txtCurrentResponseCode;
    
    private JTextField txtNaics07, txtNaics;
    
    private JTextField txtTown1, txtZone1, txtCounty1, txtOwnership1, txtMeei1;
    private JTextField txtTown2, txtZone2, txtCounty2, txtOwnership2, txtMeei2;
    
    private JTextField txtResultingCode;

    public UpdateARCSControlFile() {
        // 1. Configuración básica de la ventana
        setTitle("Update ARCS Control File");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(5, 5));
        getContentPane().setBackground(new Color(212, 208, 200)); // Color clásico de Windows
        
        // Intentar usar el estilo del sistema operativo actual
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 2. Panel principal que contendrá todas las secciones
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(new Color(212, 208, 200));

        // 3. Añadir las secciones al panel principal
        mainPanel.add(createAccountInfoPanel());
        mainPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        mainPanel.add(createIndustryCodingPanel());
        mainPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        mainPanel.add(createNonEconomicCodingPanel());
        
        add(mainPanel, BorderLayout.CENTER);
        add(createBottomPanel(), BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null); // Centrar en pantalla
        setResizable(false);
    }

    // --- SECCIÓN 1: ARS Account Information ---
    private JPanel createAccountInfoPanel() {
        JPanel panel = createTitledPanel("ARS Account Information");
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 5, 2, 5); // Margen entre componentes
        gbc.anchor = GridBagConstraints.WEST;

        // Fila 0
        gbc.gridx = 0; gbc.gridy = 0; panel.add(new JLabel("Year:"), gbc);
        yearSpinner = new JSpinner(new SpinnerNumberModel(2008, 1900, 2100, 1));
        gbc.gridx = 1; gbc.gridy = 0; panel.add(yearSpinner, gbc);

        gbc.gridx = 2; gbc.gridy = 0; panel.add(new JLabel("Batch:"), gbc);
        JPanel batchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        txtBatch1 = new JTextField("000000", 6);
        txtBatch2 = new JTextField("0000", 4);
        batchPanel.add(txtBatch1); batchPanel.add(txtBatch2);
        gbc.gridx = 3; gbc.gridy = 0; panel.add(batchPanel, gbc);

        gbc.gridx = 4; gbc.gridy = 0; panel.add(new JLabel("CO:"), gbc);
        txtCO = new JTextField("no", 3);
        gbc.gridx = 5; gbc.gridy = 0; panel.add(txtCO, gbc);

        // Fila 1
        gbc.gridx = 0; gbc.gridy = 1; panel.add(new JLabel("Account:"), gbc);
        JPanel accountPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        txtAccount1 = new JTextField("0000000001", 10);
        txtAccount2 = new JTextField("00000", 5);
        accountPanel.add(txtAccount1); accountPanel.add(txtAccount2);
        gbc.gridx = 1; gbc.gridy = 1; panel.add(accountPanel, gbc);

        gbc.gridx = 2; gbc.gridy = 1; panel.add(new JLabel("Source:"), gbc);
        cbSource = new JComboBox<>(new String[]{"ARS", "OTHER"});
        gbc.gridx = 3; gbc.gridy = 1; panel.add(cbSource, gbc);

        gbc.gridx = 4; gbc.gridy = 1; panel.add(new JLabel("CMI:"), gbc);
        txtCMI = new JTextField("00", 3);
        gbc.gridx = 5; gbc.gridy = 1; panel.add(txtCMI, gbc);

        // Fila 2
        gbc.gridx = 0; gbc.gridy = 2; panel.add(new JLabel("Circumstance:"), gbc);
        cbCircumstance = new JComboBox<>(new String[]{"None", "Option 1"});
        gbc.gridx = 1; gbc.gridy = 2; gbc.gridwidth = 3; gbc.fill = GridBagConstraints.HORIZONTAL; panel.add(cbCircumstance, gbc);
        
        gbc.gridx = 4; gbc.gridy = 2; gbc.gridwidth = 1; gbc.fill = GridBagConstraints.NONE; panel.add(new JLabel("Current Response Code:"), gbc);
        txtCurrentResponseCode = new JTextField("46", 3);
        gbc.gridx = 5; gbc.gridy = 2; panel.add(txtCurrentResponseCode, gbc);

        // Fila 3 (Botones intermedios)
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        buttonsPanel.setBackground(new Color(212, 208, 200));
        buttonsPanel.add(new JButton("2008 1"));
        buttonsPanel.add(new JButton("2007 4"));
        JButton manualCodingBtn = new JButton("Manual Coding");
        manualCodingBtn.setForeground(Color.RED);
        buttonsPanel.add(manualCodingBtn);
        
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 6; gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(buttonsPanel, gbc);

        return panel;
    }

    // --- SECCIÓN 2: Industry Coding ---
    private JPanel createIndustryCodingPanel() {
        JPanel panel = createTitledPanel("Industry Coding");
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 20, 2, 20);

        gbc.gridx = 0; gbc.gridy = 0; panel.add(new JLabel("NAICS 07:"), gbc);
        txtNaics07 = new JTextField("454311", 7);
        gbc.gridx = 1; gbc.gridy = 0; panel.add(txtNaics07, gbc);

        gbc.gridx = 2; gbc.gridy = 0; panel.add(new JLabel("NAICS:"), gbc);
        txtNaics = new JTextField("561990", 7);
        gbc.gridx = 3; gbc.gridy = 0; panel.add(txtNaics, gbc);

        return panel;
    }

    // --- SECCIÓN 3: Non Economic Coding ---
    private JPanel createNonEconomicCodingPanel() {
        JPanel panel = createTitledPanel("Non Economic Coding");
        panel.setLayout(new GridLayout(1, 2, 10, 0)); // Dos columnas

        JPanel col1 = createColumnPanel();
        txtTown1 = addLabeledField(col1, "Town:", "126");
        txtZone1 = addLabeledField(col1, "Zone:", "");
        txtCounty1 = addLabeledField(col1, "County:", "005");
        txtOwnership1 = addLabeledField(col1, "Ownership:", "5");
        txtMeei1 = addLabeledField(col1, "MEEI:", "1");

        JPanel col2 = createColumnPanel();
        txtTown2 = addLabeledField(col2, "Town:", "067");
        txtZone2 = addLabeledField(col2, "Zone:", "");
        txtCounty2 = addLabeledField(col2, "County:", "003");
        txtOwnership2 = addLabeledField(col2, "Ownership:", "5");
        txtMeei2 = addLabeledField(col2, "MEEI:", "1");

        panel.add(col1);
        panel.add(col2);
        return panel;
    }

    // --- SECCIÓN 4: Botonera Inferior ---
    private JPanel createBottomPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        panel.setBackground(new Color(212, 208, 200));

        // Etiqueta y campo de código resultante (con borde)
        JPanel resultPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        resultPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        resultPanel.setBackground(new Color(212, 208, 200));
        resultPanel.add(new JLabel("Resulting Response Code:"));
        txtResultingCode = new JTextField("46", 3);
        resultPanel.add(txtResultingCode);
        panel.add(resultPanel);

        // Botones de acción principal
        JButton btnAccept = new JButton("Accept and Save");
        btnAccept.setForeground(Color.RED);
        btnAccept.addActionListener(e -> guardarDatos()); // Evento para guardar

        JButton btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(e -> dispose()); // Cierra la ventana actual

        JButton btnHelp = new JButton("Help");
        btnHelp.setForeground(Color.RED);

        panel.add(btnAccept);
        panel.add(btnCancel);
        panel.add(btnHelp);

        return panel;
    }

    // --- MÉTODOS AUXILIARES ---

    // Crea un panel con borde gris y título blanco sobre fondo gris oscuro
    private JPanel createTitledPanel(String title) {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(160, 160, 160)); // Fondo gris oscuro de las cajas
        Border line = BorderFactory.createLineBorder(Color.GRAY);
        TitledBorder titledBorder = BorderFactory.createTitledBorder(line, title);
        titledBorder.setTitleColor(Color.WHITE); // Título en blanco
        panel.setBorder(titledBorder);
        return panel;
    }

    private JPanel createColumnPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(160, 160, 160));
        return panel;
    }

    private JTextField addLabeledField(JPanel panel, String labelText, String initialValue) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 5, 2, 5);
        gbc.anchor = GridBagConstraints.EAST;
        
        int gridy = panel.getComponentCount() / 2;
        
        gbc.gridx = 0; gbc.gridy = gridy;
        JLabel label = new JLabel(labelText);
        label.setForeground(Color.WHITE); // Texto en blanco por el fondo oscuro
        panel.add(label, gbc);
        
        gbc.gridx = 1; gbc.gridy = gridy; gbc.anchor = GridBagConstraints.WEST;
        JTextField textField = new JTextField(initialValue, 4);
        panel.add(textField, gbc);
        
        return textField;
    }

    // --- LÓGICA DE PERSISTENCIA (GUARDADO) ---
    private void guardarDatos() {
        // Usamos try-with-resources para asegurar que el archivo se cierre automáticamente
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("datos.txt", true))) {
            
            // Construimos la línea CSV recopilando los datos
            String lineaDatos = String.join(",",
                yearSpinner.getValue().toString(),
                txtBatch1.getText(), txtBatch2.getText(),
                txtCO.getText(),
                txtAccount1.getText(), txtAccount2.getText(),
                cbSource.getSelectedItem().toString(),
                txtCMI.getText(),
                cbCircumstance.getSelectedItem().toString(),
                txtCurrentResponseCode.getText(),
                txtNaics07.getText(), txtNaics.getText(),
                txtTown1.getText(), txtZone1.getText(), txtCounty1.getText(), txtOwnership1.getText(), txtMeei1.getText(),
                txtTown2.getText(), txtZone2.getText(), txtCounty2.getText(), txtOwnership2.getText(), txtMeei2.getText(),
                txtResultingCode.getText()
            );

            writer.write(lineaDatos);
            writer.newLine(); // Salto de línea para el siguiente registro
            
            JOptionPane.showMessageDialog(this, "Datos guardados correctamente en datos.txt", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar los datos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        // Ejecutar la interfaz en el Hilo de Despacho de Eventos (EDT) por seguridad
        SwingUtilities.invokeLater(() -> {
            new UpdateARCSControlFile().setVisible(true);
        });
    }
}