package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;

import model.Club;
import model.GrupoFamiliar;
import model.Socio;
import view.VClub;
import view.VSocio;

public class Controller implements ActionListener, MouseListener{
	private VClub view;
	private IConnection conn;
	private VSocio viewSocio;
	
	public Controller() {
		this.conn = new PostgresConnection();
		Club.getInstance().setTipos(conn.getTipos());
		Club.getInstance().setGrupos(conn.getGruposFamiliares());
		this.view = new VClub();
		this.view.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Agregar Grupo")) {
			viewSocio = new VSocio("Nuevo Grupo", "Titular del Grupo", "Agregar");
			viewSocio.addActionListener(this);
			viewSocio.setVisible(true);			
		} else if(e.getActionCommand().equals("Actualizar")) {
			System.out.println(e.getActionCommand());
			Socio s = (Socio) view.getListSocios().getSelectedValue();
			int nro_grupo = s.getNro_Grupo();
			int nro_socio = s.getNro_Socio();
			actualizarSocio(nro_grupo, nro_socio);
			viewSocio.setVisible(false);
		} else if (e.getActionCommand().equals("Agregar")) {
			System.out.println(e.getActionCommand());
		}
	}

	private void actualizarSocio(int nro_grupo, int nro_socio) {
		Iterator<GrupoFamiliar> itg = Club.getInstance().getGrupos().iterator();
		GrupoFamiliar gf = null;
		
		while(itg.hasNext()) {
			gf = (GrupoFamiliar) itg.next();
			if(gf.getNro_Grupo() == nro_grupo) {
				Iterator<Socio> its = gf.getSocios().iterator();
				
				while(its.hasNext()) {
					Socio s = (Socio) its.next();
					if(s.getNro_Socio() == nro_socio) {						
						s.updateSocio(
								viewSocio.getNombre(), 
								viewSocio.getApellido(), 
								viewSocio.getEmail(), 
								viewSocio.getFNac(), 
								viewSocio.getDomicilio(), 
								Club.getInstance().getTipos().get(String.valueOf(viewSocio.getTipo().charAt(0))), 
								viewSocio.getTelefono(),
								viewSocio.getCelular()
								);
						break;
					}
				}
			}
		}
		if(gf.getTitular().getNro_Socio() == nro_socio) {
			gf.getTitular().updateSocio(
					viewSocio.getNombre(), 
					viewSocio.getApellido(), 
					viewSocio.getEmail(), 
					viewSocio.getFNac(), 
					viewSocio.getDomicilio(), 
					Club.getInstance().getTipos().get(String.valueOf(viewSocio.getTipo().charAt(0))), 
					viewSocio.getTelefono(),
					viewSocio.getCelular()
					);
			view.refreshGrupos();
		}
		// TODO: Meter un UPDATE en BD.
		view.refreshInfo(gf);
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
		if (e.getSource() == view.getListSocios()) {
			if (!view.getListSocios().isSelectionEmpty()) {
				Socio s = (Socio) view.getListSocios().getSelectedValue();
				viewSocio = new VSocio("Socio", "Datos Personales", "Actualizar");
				viewSocio.addActionListener(this);
				viewSocio.setNombre(s.getNombre());
				viewSocio.setApellido(s.getApellido());
				viewSocio.setEmail(s.getEmail());
				viewSocio.setFNac(s.getF_nac());
				viewSocio.setDomicilio(s.getDomicilio());
				viewSocio.setTelefono(s.getTelefono());
				viewSocio.setCelular(s.getCelular());
				viewSocio.setTipo(s.getTipo().getTipo());
				viewSocio.setVisible(true);
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
}
