package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;

import model.Club;
import model.CuotaFamiliar;
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
		if(e.getActionCommand().equals("Nuevo Grupo")) {
			viewSocio = new VSocio("Nuevo Grupo", "Titular del Grupo", "Agregar Grupo");
			viewSocio.addActionListener(this);
			viewSocio.setVisible(true);			
		} else if(e.getActionCommand().equals("Actualizar")) {
			Socio s = (Socio) view.getListSocios().getSelectedValue();
			int nro_grupo = s.getNro_Grupo();
			int nro_socio = s.getNro_Socio();
			actualizarSocio(nro_grupo, nro_socio);
			viewSocio.setVisible(false);
		} else if (e.getActionCommand().equals("Agregar Grupo")) {
			addGrupo();
			view.refreshGrupos();
			viewSocio.setVisible(false);
		} else if (e.getActionCommand().equals("Nuevo Socio")) {
			viewSocio = new VSocio("Nuevo Socio", "Datos Personales", "Agregar Socio");
			viewSocio.addActionListener(this);
			viewSocio.setVisible(true);					
		} else if (e.getActionCommand().equals("Agregar Socio")) {
			GrupoFamiliar gf = (GrupoFamiliar) view.getListGrupos().getSelectedValue();
			int nro_grupo = gf.getNro_Grupo();
			addSocio(nro_grupo);
			view.refreshInfo(gf);
			viewSocio.setVisible(false);					
		} else if (e.getActionCommand().equals("Pagar Cuota")) {
			GrupoFamiliar gf = (GrupoFamiliar) view.getListGrupos().getSelectedValue();
			if(!view.getListCoutas().isSelectionEmpty()) {
				CuotaFamiliar cf = (CuotaFamiliar) view.getListCoutas().getSelectedValue();
				pagarCuota(gf.getNro_Grupo(), cf.getNro_cuota());
			}
			view.refreshInfo(gf);
		} 
	}
	
	public void pagarCuota(int nro_grupo, int nro_cuota) {
		Club.getInstance().getGrupo(nro_grupo).pagarCuota(nro_cuota);
		// TODO: Pagar la cuota en base de datos.
	}
	
	public void addGrupo() {
		int nro_grupo = Club.getInstance().getGrupos().get(Club.getInstance().getGrupos().size() - 1).getNro_Grupo() + 1;
		Socio titular = createSocio(nro_grupo, 1);
		GrupoFamiliar gf = new GrupoFamiliar(nro_grupo, titular);
		gf.addSocio(titular);
		Club.getInstance().addGrupo(gf);
		// TODO: Agregar el grupo en base de datos.
	}
	
	public void addSocio(int nro_grupo) {
		GrupoFamiliar gf = Club.getInstance().getGrupo(nro_grupo);
		int nro_socio = gf.getSocios().get(gf.getSocios().size() - 1).getNro_Socio() + 1;
		Socio socio = createSocio(nro_grupo, nro_socio);
		gf.addSocio(socio);
		// TODO: Agregar el socio al grupo en base de datos.
	}
	
	private Socio createSocio(int nro_grupo, int nro_socio) {
		return new Socio(
				nro_grupo, 
				nro_socio, 
				viewSocio.getNombre(),
				viewSocio.getApellido(), 
				viewSocio.getEmail(), 
				viewSocio.getFNac(), 
				viewSocio.getDomicilio(), 
				Club.getInstance().getTipos().get(String.valueOf(viewSocio.getTipo().charAt(0))), 
				viewSocio.getCelular(),
				viewSocio.getTelefono()
				);
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
		// conn.updateSocio(nro_grupo, nro_socio, datos);
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
