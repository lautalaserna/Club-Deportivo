package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import controller.Controller;
import model.Club;
import model.CuotaFamiliar;
import model.GrupoFamiliar;
import model.Socio;

public class VClub extends JFrame implements MouseListener{
	private JPanel contentPane;
	private JList listGrupos;
	private JList listSocios;
	private JList listCuotas;
	private DefaultListModel<GrupoFamiliar> modelGrupos;
	private DefaultListModel<Socio> modelSocios;
	private DefaultListModel<CuotaFamiliar> modelCuotas;
	private JButton btnAgregarGrupo;
	private JButton btnAgregarSocio;
	private JLabel lblNewLabel;
	private JLabel lblTitular;
	private JLabel lblNewLabel_1;
	private JLabel lblDeuda;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VClub frame = new VClub();
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
	public VClub() {
		setTitle("Club Deportivo");
		setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 12));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(550, 250, 888, 630);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelSocios = new JPanel();
		panelSocios.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Informaci\u00F3n del Grupo", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelSocios.setBounds(358, 10, 510, 256);
		contentPane.add(panelSocios);
		panelSocios.setLayout(null);
		
		JPanel panelList2 = new JPanel();
		panelList2.setBounds(20, 83, 467, 113);
		panelSocios.add(panelList2);
		panelList2.setLayout(new BorderLayout(0, 0));
		
		JPanel panelGruposFamiliares = new JPanel();
		panelGruposFamiliares.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Grupos Familiares", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelGruposFamiliares.setBounds(10, 10, 350, 573);
		contentPane.add(panelGruposFamiliares);
		panelGruposFamiliares.setLayout(null);
		
		JPanel panelList1 = new JPanel();
		panelList1.setBounds(21, 24, 308, 486);
		panelGruposFamiliares.add(panelList1);
		panelList1.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane1 = new JScrollPane();
		panelList1.add(scrollPane1);
		
		listGrupos = new JList();
		scrollPane1.setViewportView(listGrupos);
		listGrupos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		modelGrupos = new DefaultListModel<GrupoFamiliar>();
		listGrupos.setModel(modelGrupos);
		
		btnAgregarGrupo = new JButton("Agregar Grupo");
		btnAgregarGrupo.setBounds(189, 523, 140, 32);
		panelGruposFamiliares.add(btnAgregarGrupo);
		btnAgregarGrupo.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		listGrupos.addMouseListener(this);
		
		JScrollPane scrollPane2 = new JScrollPane();
		panelList2.add(scrollPane2);
		
		listSocios = new JList();
		scrollPane2.setViewportView(listSocios);
		listSocios.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		modelSocios = new DefaultListModel<Socio>();
		listSocios.setModel(modelSocios);
		
		btnAgregarSocio = new JButton("Agregar Socio");
		btnAgregarSocio.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		btnAgregarSocio.setBounds(347, 206, 140, 32);
		panelSocios.add(btnAgregarSocio);
		
		lblNewLabel = new JLabel("Titular:");
		lblNewLabel.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 14));
		lblNewLabel.setBounds(20, 24, 59, 24);
		panelSocios.add(lblNewLabel);
		
		lblTitular = new JLabel("");
		lblTitular.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		lblTitular.setBounds(82, 24, 295, 24);
		panelSocios.add(lblTitular);
		
		lblNewLabel_1 = new JLabel("Integrantes:");
		lblNewLabel_1.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 14));
		lblNewLabel_1.setBounds(20, 58, 140, 24);
		panelSocios.add(lblNewLabel_1);
		
		JPanel panelCuotas = new JPanel();
		panelCuotas.setLayout(null);
		panelCuotas.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Cuotas del Grupo", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelCuotas.setBounds(358, 263, 510, 320);
		contentPane.add(panelCuotas);
		
		JPanel panelList3 = new JPanel();
		panelList3.setBounds(20, 29, 229, 266);
		panelCuotas.add(panelList3);
		panelList3.setLayout(new BorderLayout(0, 0));
		
		listCuotas = new JList();
		JScrollPane scrollPane3 = new JScrollPane();
		panelList3.add(scrollPane3);
		scrollPane3.setViewportView(listCuotas);
		listCuotas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		modelCuotas = new DefaultListModel<CuotaFamiliar>();
		listCuotas.setModel(modelCuotas);
		
		JButton btnPagar = new JButton("Pagar Cuota");
		btnPagar.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		btnPagar.setBounds(310, 225, 140, 70);
		panelCuotas.add(btnPagar);
		
		JLabel lblNewLabel_2 = new JLabel("Deuda TOTAL:");
		lblNewLabel_2.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 14));
		lblNewLabel_2.setBounds(259, 29, 123, 24);
		panelCuotas.add(lblNewLabel_2);
		
		lblDeuda = new JLabel("");
		lblDeuda.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 14));
		lblDeuda.setBounds(379, 29, 109, 24);
		panelCuotas.add(lblDeuda);
				
		setVisible(true);
		refreshGrupos();
	}
	
	public void addActionListener(Controller c) {
		this.btnAgregarGrupo.addActionListener(c);
		this.btnAgregarSocio.addActionListener(c);
		this.listSocios.addMouseListener(c);
	}
	
	public void refreshGrupos() {
		this.modelGrupos.clear();
		Iterator<GrupoFamiliar> it = Club.getInstance().getGrupos().iterator();
		while (it.hasNext()) {
			GrupoFamiliar gf = it.next();
			this.modelGrupos.addElement(gf);
		}
		this.listGrupos.repaint();
	}
	
	public void refreshInfo(GrupoFamiliar gf) {
		this.lblTitular.setText(gf.getTitular().getNombre() + " " + gf.getTitular().getApellido());
		this.lblDeuda.setText(String.valueOf(gf.getDeudaTotal()));
		this.modelSocios.clear();
		this.modelCuotas.clear();
		
		Iterator<Socio> it = gf.getSocios().iterator();
		while (it.hasNext()) {
			Socio s = it.next();
			this.modelSocios.addElement(s);
		}
		this.listSocios.repaint();
		
		Iterator<CuotaFamiliar> it2 = gf.getCuotas().iterator();
		while(it2.hasNext()) {
			CuotaFamiliar cf = it2.next();
			this.modelCuotas.addElement(cf);
		}
		this.listCuotas.repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getSource() == this.listGrupos) {
			if(!this.listGrupos.isSelectionEmpty()) {
				refreshInfo((GrupoFamiliar) this.listGrupos.getSelectedValue());				
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public JList getListSocios() {
		return this.listSocios;
	}
}
