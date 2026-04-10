package main;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class Ventana extends JFrame{

	public Ventana() {
		
		//CONFIGURACIONES BÁSICAS 
		this.setSize(1000, 620); 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setMinimumSize(new Dimension(200,200));
		this.setMaximumSize(new Dimension(1000,800));
		this.setLocationRelativeTo(null);
		this.setTitle("Hola");
		this.setLayout(null); 
		this.getContentPane().setBackground(Color.gray); 

		
		try {
			Image iconImage = ImageIO.read(getClass().getResource("/images/8152506.png"));
	        this.setIconImage(iconImage);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.menu();
		
		this.router("login");
		
		this.setVisible(true);
		this.repaint();
		
	}
	
	public void router(String target)
	{
		this.getContentPane().removeAll();
		
		if(target.equals("login"))
			this.login();
		
		if(target.equals("registro"))
			this.registro();
		
		if(target.equals("forgot"))
			this.recuperarContrasena();
		
		this.repaint();
		this.revalidate();
	}
	
	public void login()
	{
		
		JTextField textField;
		JPasswordField passwordField;
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255)); 
		panel.setLocation(0, 0);
		panel.setLayout(null);
		panel.setSize(1000, 600); 
		
		JLabel lblNewLabel = new JLabel("UABCS - DASC\n");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Kefa", Font.PLAIN, 24));
		lblNewLabel.setBounds(107, 35, 210, 26);
		lblNewLabel.setHorizontalAlignment(JLabel.CENTER);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_8 = new JLabel("");
		lblNewLabel_8.setBounds(36, 186, 353, 81);
		lblNewLabel_8.setBorder(BorderFactory.createTitledBorder("Correo electrónico"));
		panel.add(lblNewLabel_8);
		
		textField = new JTextField();
		textField.setBackground(new Color(218, 230, 225));
		textField.setBounds(49, 211, 328, 42);
		panel.add(textField);
		textField.setBorder(new LineBorder(new Color(91, 253, 255), 1, true));
		textField.setBorder(null);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBackground(new Color(218, 230, 225));
		passwordField.setBounds(49, 300, 295, 42);
		panel.add(passwordField);
		
		JButton btnNewButton = new JButton("Acceder");
		btnNewButton.setBackground(new Color(0, 0, 0));
		btnNewButton.setOpaque(true);
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String passText = new String(passwordField.getPassword());
				Boolean flag1 = false, flag2 = false;
				
				
				if( passText.equals("") ) {
					
					passwordField.setBorder(BorderFactory.createLineBorder(Color.red,2));
					
				}else {
					
					passwordField.setBorder(BorderFactory.createLineBorder(Color.green,2));
					flag1 = true;
				}
				
				
				if(textField.getText().equals("")) {
					textField.setBorder(BorderFactory.createLineBorder(Color.red,2));
				}else {
					
					textField.setBorder(BorderFactory.createLineBorder(Color.green,2));
					flag2 = true;
				}
				
				if(flag1 && flag2) {
					
					if(textField.getText().equals("fake@mail.com") ) {
						if(passText.equals("12345")) {
							
							JOptionPane.showMessageDialog(null, "Bienvenido.");
							
						}else {
							JOptionPane.showMessageDialog(null, "Error al acceder","verifique su información",JOptionPane.WARNING_MESSAGE);
						}
					}else {
						JOptionPane.showMessageDialog(null, "Error al acceder","verifique su información",JOptionPane.WARNING_MESSAGE);
					}
					
					
				}
				
			}
		});
		btnNewButton.setBounds(36, 413, 341, 37);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Sabiduría como meta, patria como destino ");
		lblNewLabel_1.setBounds(69, 73, 308, 16);
		lblNewLabel_1.setHorizontalAlignment(JLabel.CENTER);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_6 = new JLabel("");
		
		lblNewLabel_6.setIcon(new ImageIcon(this.getClass().getResource("/images/redd-francisco-9o8YdYGTT64-unsplash.jpg")));
		lblNewLabel_6.setBounds(531, 6, 383, 494);
		lblNewLabel_6.setBorder(BorderFactory.createLineBorder(Color.white,5,true));
		panel.add(lblNewLabel_6);
		
		JLabel lblNewLabel_2_1 = new JLabel("Ingresa al panel administrador");
		lblNewLabel_2_1.setForeground(Color.BLACK);
		lblNewLabel_2_1.setFont(new Font("Lucida Grande", Font.PLAIN, 21));
		lblNewLabel_2_1.setBounds(49, 109, 340, 39);
		lblNewLabel_2_1.setHorizontalAlignment(JLabel.CENTER);
		panel.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_8_1 = new JLabel("");
		lblNewLabel_8_1.setBorder(BorderFactory.createTitledBorder("Contraseña"));
		lblNewLabel_8_1.setBounds(36, 279, 353, 81);
		panel.add(lblNewLabel_8_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		
		lblNewLabel_2.setIcon(new ImageIcon(this.getClass().getResource("/images/hidden.png")));
		lblNewLabel_2.setBounds(351, 315, 24, 16);
		panel.add(lblNewLabel_2);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Recordar contraseña");
		chckbxNewCheckBox.setBounds(36, 372, 180, 23);
		panel.add(chckbxNewCheckBox);
		
		JLabel lblNewLabel_3 = new JLabel("¿Aún no tienes cuenta?");
		lblNewLabel_3.setBounds(225, 462, 152, 16);
		panel.add(lblNewLabel_3);
		
		JButton btnNewButton2 = new JButton("Crea una aquí");
		btnNewButton2.setBackground(new Color(0, 0, 0));
		btnNewButton2.setOpaque(true);
		btnNewButton2.setForeground(new Color(0, 0, 0));
		btnNewButton2.setBounds(205, 490, 200, 20);
		btnNewButton2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				router("registro");
				
			}});
		
		panel.add(btnNewButton2);
		
		this.add(panel);
	}
	
	public void registro() {
		
		JPanel rgs_container = new JPanel();
		rgs_container.setBounds(500, 50, 400, 600);
		rgs_container.setOpaque(true);
		rgs_container.setBackground(Color.green);
		rgs_container.setLayout(null);
		this.add(rgs_container);

		
		JLabel bio_tag = new JLabel("BIO");
		bio_tag.setBounds(50,50,300,40);
		bio_tag.setHorizontalAlignment(JLabel.CENTER);
		bio_tag.setOpaque(true);
		rgs_container.add(bio_tag);
		
		JTextArea bio_text = new JTextArea();
		bio_text.setLocation(50, 100);
		bio_text.setSize(300,80);
		rgs_container.add(bio_text);
		
		//label preferences
		
		JCheckBox opt_sweet = new JCheckBox("Dulce");
		opt_sweet.setBounds(50, 200, 100, 40);
		opt_sweet.setOpaque(true);
		opt_sweet.setForeground(Color.red);
		rgs_container.add(opt_sweet);
		
		JCheckBox opt_salty = new JCheckBox("Salado");
		opt_salty.setBounds(150, 200, 100, 40);
		rgs_container.add(opt_salty);
		
		JCheckBox opt_healty = new JCheckBox("Saludable");
		opt_healty.setBounds(250, 200, 100, 40);
		rgs_container.add(opt_healty);
		
		
		//label terms 
		JRadioButton accept_terms = new JRadioButton("Acepto los términos");
		accept_terms.setBounds(50, 260, 180, 40);
		rgs_container.add(accept_terms);
		
		JRadioButton reject_terms = new JRadioButton("Rechazo los términos");
		reject_terms.setBounds(220, 260, 200, 40);
		rgs_container.add(reject_terms);
		
		ButtonGroup terms = new ButtonGroup();
		terms.add(accept_terms);
		terms.add(reject_terms); 
		
		//combo 
		String [] colonias = {"Camino real","Arcoiris","Villas del encanto","8 de octubre"};
		
		JComboBox colonias_combo = new JComboBox(colonias);
		colonias_combo.setBounds(50, 300, 300, 50);
		
		rgs_container.add(colonias_combo); 
		
		JButton register_btn = new JButton("Crear cuenta");
		register_btn.setBounds(50, 340, 300, 60);
		rgs_container.add(register_btn);
		
		JButton login_btn = new JButton("¿Ya tienes cuenta?");
		login_btn.setBounds(40, 390, 300, 60);
		rgs_container.add(login_btn);
		
		login_btn.addActionListener(e->{
			this.router("login");
		});
	}
	
	public void recuperarContrasena() {
		
		JPanel rgs_container = new JPanel();
		rgs_container.setBounds(500, 50, 400, 600);
		rgs_container.setOpaque(true);
		rgs_container.setBackground(Color.green);
		rgs_container.setLayout(null);
		this.add(rgs_container);

		
		JLabel bio_tag = new JLabel("OLVIDÉ MI CONTRASEÑA");
		bio_tag.setBounds(50,50,300,40);
		bio_tag.setHorizontalAlignment(JLabel.CENTER);
		bio_tag.setOpaque(true);
		rgs_container.add(bio_tag);
		
		 
	}
	
	public void users()
	{
		JPanel panel_users = new JPanel();
		panel_users.setSize(900, 500);
		panel_users.setLocation(50, 50);
		panel_users.setBackground(Color.decode("#DDDEA6"));
		panel_users.setLayout(null);
		this.add(panel_users);
		
		String [] table_head = {"No. Control","Nombre","Apellidos","Correo electrónico","Semestre","Carrera","Acciones"};
		
		Object [][] table_body = {
			    {"20231001","Carlos","Ramírez López","carlos.ramirez@correo.com","3","Ingeniería en Sistemas Computacionales","Editar"},
			    {"20231002","María","González Pérez","maria.gonzalez@correo.com","5","Ingeniería Industrial","Editar"},
			    {"20231003","Luis","Hernández Torres","luis.hernandez@correo.com","1","Licenciatura en Administración","Editar"},
			    {"20231004","Ana","Martínez Ruiz","ana.martinez@correo.com","7","Ingeniería Civil","Editar"},
			    {"20231005","Jorge","Sánchez Morales","jorge.sanchez@correo.com","4","Ingeniería Mecatrónica","Editar"},
			    {"20231006","Fernanda","Castillo Díaz","fernanda.castillo@correo.com","2","Licenciatura en Psicología","Editar"},
			    {"20231007","Diego","Vargas Romero","diego.vargas@correo.com","6","Ingeniería en Tecnologías de la Información","Editar"}
			};
		
		JTable studens = new JTable(table_body,table_head);
		
		JScrollPane final_table = new JScrollPane(studens);
		final_table.setSize(700,150);
		final_table.setLocation(100, 100);
		
		panel_users.add(final_table);
		
		panel_users.repaint();
	}
	
	public void menu()
	{
		JMenuBar barra = new JMenuBar();
		this.setJMenuBar(barra);
		
		JMenu menu1 = new JMenu("Archivo");
		barra.add(menu1);
		
		JMenuItem opt1_mi = new JMenuItem("Abrir");
		menu1.add(opt1_mi);
		
		JMenuItem opt2_mi = new JMenuItem("Nuevo");
		menu1.add(opt2_mi);
		
		JMenuItem opt3_mi = new JMenuItem("Cerrar");
		menu1.add(opt3_mi);
		
		menu1.addSeparator();
		
		JMenu menu2 = new JMenu("Guardar");
		menu1.add(menu2);
		
		JMenuItem opt4_mi = new JMenuItem("Guardar");
		menu2.add(opt4_mi);
		
		JMenuItem opt5_mi = new JMenuItem("Guardar como");
		menu2.add(opt5_mi);
		
		JMenu menu3 = new JMenu("Cuenta");
		barra.add(menu3);
		
		JMenuItem opt_access = new JMenuItem("Acceder");
		opt_access.addActionListener(e ->{
			this.router("login");
		});
		menu3.add(opt_access);
		
		JMenuItem opt_register = new JMenuItem("Registro");
		opt_register.addActionListener(e ->{
			this.router("registro");
		});
		menu3.add(opt_register);
		
		JMenuItem opt_forgot = new JMenuItem("Recuperar");
		opt_forgot.addActionListener(e ->{
			this.router("forgot");
		});
		menu3.add(opt_forgot);
		
	}
	
	
}














