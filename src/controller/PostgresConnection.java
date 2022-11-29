package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import model.Club;
import model.CuotaFamiliar;
import model.GrupoFamiliar;
import model.Socio;
import model.Categoria;

public class PostgresConnection implements IConnection {
	public static final String URL = "jdbc:postgresql://localhost/club-deportivo";
	public static final String USERNAME = "libertya";
	public static final String PASSWORD = "libertya";
	private static Connection conn = null;
	
	public Connection connect() {
		try {
			if(conn == null) {
				Class.forName("org.postgresql.Driver");
				conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);				
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return conn;
	}
	
	public ArrayList<GrupoFamiliar> getGruposFamiliares() {
		ArrayList<GrupoFamiliar> grupos = new ArrayList<GrupoFamiliar> ();
		Connection conn = connect();
		
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM Grupo_Familiar");
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				int nro_grupo = rs.getInt("nro_grupo");
				int nro_titular = rs.getInt("nro_socio");

				Socio titular = getSocio(nro_grupo, nro_titular);
				GrupoFamiliar grupo = new GrupoFamiliar(nro_grupo, titular);
				
				PreparedStatement ps2 = conn.prepareStatement("SELECT nro_socio FROM Socio WHERE nro_grupo = " + nro_grupo);
				ResultSet rs2 = ps2.executeQuery();
				while(rs2.next()) {
					int nro_socio = rs2.getInt("nro_socio");
					Socio socio = getSocio(nro_grupo, nro_socio);
					grupo.addSocio(socio);
				}
				grupo.setCuotas(getCuotas(nro_grupo));
				grupos.add(grupo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return grupos;
	}
	
	public ArrayList<CuotaFamiliar> getCuotas(int nro_grupo){
		ArrayList<CuotaFamiliar> cuotas = new ArrayList<CuotaFamiliar>();
		Connection conn = connect();	
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT cf.nro_grupo, cf.nro_cuota, cf.monto_total, cf.periodo, cf.f_pago, mb.monto AS monto_base "
														+ "FROM Cuota_Familiar cf " 
														+ "INNER JOIN monto_base mb ON mb.id_monto = cf.id_monto  "
														+ "WHERE nro_grupo = " + nro_grupo + " "
														+ "ORDER BY cf.periodo");
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				CuotaFamiliar cf = new CuotaFamiliar();
				cf.setNro_grupo(rs.getInt("nro_grupo"));
				cf.setNro_cuota(rs.getInt("nro_cuota"));
				cf.setMontoTotal(rs.getFloat("monto_total"));
				cf.setPeriodo(rs.getString("periodo"));
				cf.setMontoBase(rs.getFloat("monto_base"));
				cf.setFechaPago(rs.getString("f_pago"));
				cuotas.add(cf);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cuotas;
	}
	
	public Socio getSocio(int nro_grupo, int nro_socio) {
		Connection conn = connect();
		Socio socio = new Socio();		
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * "
														+ "FROM Socio "
														+ "WHERE nro_grupo = " + nro_grupo + " "
															+ "AND nro_socio = " + nro_socio);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				socio.setNro_Grupo(rs.getInt("nro_grupo"));
				socio.setNro_Socio(rs.getInt("nro_socio"));
				socio.setNombre(rs.getString("nombre"));
				socio.setApellido(rs.getString("apellido"));
				socio.setEmail(rs.getString("email"));
				socio.setF_nac(rs.getString("f_nac"));
				socio.setDomicilio(rs.getString("domicilio"));
				
				String tipo = rs.getString("tipo");
				socio.setTipo(Club.getInstance().getTipos().get(tipo));
				
				socio.setCelular(rs.getString("celular"));
				socio.setTelefono(rs.getString("telefono"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return socio;
	}
	
	public Map<String,Categoria> getTipos(){
		Map<String,Categoria> tipos = new HashMap<String,Categoria>();
		Connection conn = connect();
		
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM Categoria");
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				float porcentaje = rs.getFloat("porcentaje");
				String tipo = rs.getString("tipo");
				String name = "";
				
				if (tipo.equals("I")) {
					name = "Infantil";
				} else if (tipo.equals("M")) {
					name = "Mayor";
				} else if (tipo.equals("V")) {
					name = "Vitalicio";
				}
				
				Categoria t = new Categoria(name, porcentaje);
				
				tipos.put(tipo, t);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return tipos;
	}
	
	public float getMontoBase() {
		float monto = 0;
		Connection conn = connect();
		
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT monto FROM Monto_Base ORDER BY periodo DESC LIMIT 1");
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				monto = rs.getFloat("monto");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return monto;
	}
}
