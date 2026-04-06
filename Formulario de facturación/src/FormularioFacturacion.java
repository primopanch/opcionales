import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FormularioFacturacion extends JFrame {

    private JTextField txtInvoiceNumber, txtCompanyName, txtRentalRate;
    private JComboBox<String> cbPaymentType;
    private JSpinner spinDate;

    public FormularioFacturacion() {
        setTitle("Invoice Entry Form - Sistema de Facturación");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JLabel lblTitle = new JLabel("Formulario de Ingreso de Facturas", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitle.setOpaque(true);
        lblTitle.setBackground(new Color(0, 51, 153));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(lblTitle, BorderLayout.NORTH);

        JPanel panelCentro = new JPanel(new BorderLayout());
        panelCentro.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel panelFormulario = new JPanel(new GridLayout(6, 2, 10, 15));

        txtInvoiceNumber = new JTextField();
        txtCompanyName = new JTextField();
        txtRentalRate = new JTextField();
        
        String[] opcionesPago = {"Tarjeta de Crédito", "Transferencia Bancaria", "Efectivo", "Cheque"};
        cbPaymentType = new JComboBox<>(opcionesPago);

        SpinnerDateModel modeloFecha = new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH);
        spinDate = new JSpinner(modeloFecha);
        JSpinner.DateEditor editorFecha = new JSpinner.DateEditor(spinDate, "dd/MM/yyyy");
        spinDate.setEditor(editorFecha);

        panelFormulario.add(new JLabel("Invoice Number:"));
        panelFormulario.add(txtInvoiceNumber);

        panelFormulario.add(new JLabel("Company Name:"));
        panelFormulario.add(txtCompanyName);

        panelFormulario.add(new JLabel("Tipo de Pago:"));
        panelFormulario.add(cbPaymentType);

        panelFormulario.add(new JLabel("Start Date:"));
        panelFormulario.add(spinDate);

        panelFormulario.add(new JLabel("Rental Rate / Charge ($):"));
        panelFormulario.add(txtRentalRate);

        panelCentro.add(panelFormulario, BorderLayout.CENTER);
        add(panelCentro, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        
        JButton btnSave = new JButton("Save");
        JButton btnClose = new JButton("Close");

        panelBotones.add(btnSave);
        panelBotones.add(btnClose);
        add(panelBotones, BorderLayout.SOUTH);

        btnClose.addActionListener(e -> System.exit(0));

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarFactura();
            }
        });
    }

    private void guardarFactura() {
        String invoiceNumber = txtInvoiceNumber.getText();
        String company = txtCompanyName.getText();
        String payment = (String) cbPaymentType.getSelectedItem();
        String rate = txtRentalRate.getText();
        
        Date fechaObtenida = (Date) spinDate.getValue();
        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
        String startDate = formateador.format(fechaObtenida);

        if (invoiceNumber.trim().isEmpty() || company.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, llene al menos el Invoice Number y Company Name.", "Campos incompletos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try (FileWriter fw = new FileWriter("facturas.txt", true);
             PrintWriter pw = new PrintWriter(fw)) {
             
            pw.println("----- FACTURA REGISTRADA -----");
            pw.println("Fecha de Sistema: " + new Date());
            pw.println("Invoice Number: " + invoiceNumber);
            pw.println("Company Name:   " + company);
            pw.println("Payment Type:   " + payment);
            pw.println("Start Date:     " + startDate);
            pw.println("Rental Rate:    $" + rate);
            pw.println("------------------------------");
            pw.println();

            JOptionPane.showMessageDialog(this, "Factura guardada correctamente en 'facturas.txt'", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            
            txtInvoiceNumber.setText("");
            txtCompanyName.setText("");
            txtRentalRate.setText("");
            
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error al escribir el archivo: " + ex.getMessage(), "Error I/O", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new FormularioFacturacion().setVisible(true);
        });
    }
}