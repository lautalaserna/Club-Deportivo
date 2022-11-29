package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import controller.Controller;

import javax.swing.JButton;
import javax.swing.border.EtchedBorder;
import java.awt.Color;

public class VSocio extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JButton btnAceptar;
	private JTextField textNombre;
	private JTextField textApellido;
	private JTextField textEmail;
	private JTextField textFNac;
	private JTextField textDomicilio;
	private JTextField textTelefono;
	private JTextField textCelular;
	private JTextField textTipo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VSocio frame = new VSocio("Socio", "Datos Personales", "Aceptar");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VSocio(String titulo, String data, String command) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle(titulo);
		setBounds(900, 320, 366, 440);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel = new JPanel();
		panel.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), data,
				TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 10, 330, 373);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		lblNewLabel.setBounds(24, 31, 80, 25);
		panel.add(lblNewLabel);

		textNombre = new JTextField();
		textNombre.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		textNombre.setColumns(10);
		textNombre.setBounds(105, 31, 200, 25);
		panel.add(textNombre);

		btnAceptar = new JButton(command);
		btnAceptar.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		btnAceptar.setBounds(166, 321, 140, 32);
		panel.add(btnAceptar);

		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		lblApellido.setBounds(25, 65, 80, 25);
		panel.add(lblApellido);

		textApellido = new JTextField();
		textApellido.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		textApellido.setColumns(10);
		textApellido.setBounds(106, 65, 200, 25);
		panel.add(textApellido);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		lblEmail.setBounds(25, 100, 80, 25);
		panel.add(lblEmail);

		textEmail = new JTextField();
		textEmail.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		textEmail.setColumns(10);
		textEmail.setBounds(106, 100, 200, 25);
		panel.add(textEmail);

		JLabel lblFechaNac = new JLabel("Fecha Nac:");
		lblFechaNac.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		lblFechaNac.setBounds(25, 135, 80, 25);
		panel.add(lblFechaNac);

		textFNac = new JTextField();
		textFNac.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		textFNac.setColumns(10);
		textFNac.setBounds(106, 135, 200, 25);
		panel.add(textFNac);

		JLabel lblDomicilio = new JLabel("Domicilio:");
		lblDomicilio.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		lblDomicilio.setBounds(25, 170, 80, 25);
		panel.add(lblDomicilio);

		textDomicilio = new JTextField();
		textDomicilio.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		textDomicilio.setColumns(10);
		textDomicilio.setBounds(106, 170, 200, 25);
		panel.add(textDomicilio);

		JLabel lblTelfono = new JLabel("Teléfono:");
		lblTelfono.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		lblTelfono.setBounds(25, 205, 80, 25);
		panel.add(lblTelfono);

		textTelefono = new JTextField();
		textTelefono.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		textTelefono.setColumns(10);
		textTelefono.setBounds(106, 205, 200, 25);
		panel.add(textTelefono);

		JLabel lblCelular = new JLabel("Celular:");
		lblCelular.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		lblCelular.setBounds(25, 240, 80, 25);
		panel.add(lblCelular);

		textCelular = new JTextField();
		textCelular.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		textCelular.setColumns(10);
		textCelular.setBounds(106, 240, 200, 25);
		panel.add(textCelular);

		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		lblTipo.setBounds(25, 275, 80, 25);
		panel.add(lblTipo);

		textTipo = new JTextField();
		textTipo.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		textTipo.setColumns(10);
		textTipo.setBounds(106, 275, 200, 25);
		panel.add(textTipo);
	}

	public void addActionListener(Controller c) {
		this.btnAceptar.addActionListener(c);
	}

	public String getNombre() {
		return this.textNombre.getText();
	}

	public String getApellido() {
		return this.textApellido.getText();
	}

	public String getEmail() {
		return this.textEmail.getText();
	}

	public String getFNac() {
		return this.textFNac.getText();
	}

	public String getDomicilio() {
		return this.textDomicilio.getText();
	}

	public String getTelefono() {
		return this.textTelefono.getText();
	}

	public String getCelular() {
		return this.textCelular.getText();
	}

	public String getTipo() {
		return this.textTipo.getText();
	}
	
	public void setNombre(String str) {
		this.textNombre.setText(str);
	}
	
	public void setApellido(String str) {
		this.textApellido.setText(str);
	}
	
	public void setEmail(String str) {
		this.textEmail.setText(str);
	}
	
	public void setFNac(String str) {
		this.textFNac.setText(str);
	}
	
	public void setTelefono(String str) {
		this.textTelefono.setText(str);
	}
	
	public void setCelular(String str) {
		this.textCelular.setText(str);
	}
	
	public void setTipo(String str) {
		this.textTipo.setText(str);
	}
	
	public void setDomicilio(String str) {
		this.textDomicilio.setText(str);
	}
}
