package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
	public static final String USERNAME = "postgres";
	public static final String PASSWORD = "1234";
	private static Connection conn = null;
	
	public Connection connect() {
		try {
			if(conn == null) {
				Class.forName("org.postgresql.Driver");
				conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public ArrayList<GrupoFamiliar> getGruposFamiliares() throws Exception{
		ArrayList<GrupoFamiliar> grupos = new ArrayList<GrupoFamiliar> ();
		Connection conn = connect();
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM Grupo_Familiar ORDER BY nro_grupo");
		ResultSet rs = ps.executeQuery();
		
		while (rs.next()) {
			int nro_grupo = rs.getInt("nro_grupo");
			int nro_titular = rs.getInt("nro_socio");

			Socio titular = getSocio(nro_grupo, nro_titular);
			GrupoFamiliar grupo = new GrupoFamiliar(nro_grupo, titular);
			
			PreparedStatement ps2 = conn.prepareStatement("SELECT nro_socio "
														+ "FROM Socio "
														+ "WHERE nro_grupo = " + nro_grupo + " "
														+ "ORDER BY nro_grupo, nro_socio");
			ResultSet rs2 = ps2.executeQuery();
			while(rs2.next()) {
				int nro_socio = rs2.getInt("nro_socio");
				Socio socio = getSocio(nro_grupo, nro_socio);
				grupo.addSocio(socio);
			}
			grupo.setCuotas(getCuotas(nro_grupo));
			grupos.add(grupo);
		}
		return grupos;
	}
	
	public ArrayList<CuotaFamiliar> getCuotas(int nro_grupo) throws Exception {
		ArrayList<CuotaFamiliar> cuotas = new ArrayList<CuotaFamiliar>();
		Connection conn = connect();	
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
		
		return cuotas;
	}
	
	public Socio getSocio(int nro_grupo, int nro_socio) throws Exception {
		Connection conn = connect();
		Socio socio = new Socio();		
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
		
		return socio;
	}
	
	public Map<String,Categoria> getTipos() throws Exception {
		Map<String,Categoria> tipos = new HashMap<String,Categoria>();
		Connection conn = connect();
		
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
			
			Categoria c = new Categoria(name, porcentaje);
			
			tipos.put(tipo, c);
		}
		
		return tipos;
	}
	
	public void addSocio(int nro_grupo, int nro_socio, String nombre, String apellido, String email, String f_nac,
			String domicilio, String tipo, String celular, String telefono) throws Exception {
		Connection conn = connect();
		String sql = "INSERT INTO socio(nro_grupo, nro_socio, nombre, apellido, email, f_nac, domicilio, tipo, celular, telefono) "
				+ "VALUES("
				+ nro_grupo + ", "
				+ nro_socio + ", "
				+ "'" + nombre + "', "
				+ "'" + apellido + "', "
				+ "'" + email + "', "
				+ "'" + f_nac + "', "
				+ "'" + domicilio + "', "
				+ "'" + tipo + "', "
				+ "'" + celular + "', "
				+ "'" + telefono + "')";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.executeUpdate();
	}

	public void updateSocio(int nro_grupo, int nro_socio, String nombre, String apellido, String email, String f_nac,
			String domicilio, String tipo, String celular, String telefono) throws Exception {
		Connection conn = connect();
		String sql = "UPDATE socio "
				+ "SET "
				+ "nro_grupo = " + nro_grupo + ", "
				+ "nro_socio = " + nro_socio + ", "
				+ "nombre = '" + nombre + "', "
				+ "apellido = '" + apellido + "', "
				+ "email = '" + email + "', "
				+ "f_nac = '" + f_nac + "', "
				+ "domicilio = '" + domicilio + "', "
				+ "tipo = '" + tipo + "', "
				+ "celular = '" + celular + "', "
				+ "telefono = '" + telefono + "' "
				+ "WHERE nro_grupo = " + nro_grupo + " AND nro_socio = " + nro_socio;
		System.out.println(sql);
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.executeUpdate();
	}
	
	public void addGrupo(String nombre, String apellido, String email, String f_nac,
			String domicilio, String tipo, String celular, String telefono) throws Exception {
		Connection conn = connect();
		String sql = "CALL create_grupo("
				+ "'" + nombre + "', "
				+ "'" + apellido + "', "
				+ "'" + email + "', "
				+ "'" + f_nac + "', "
				+ "'" + domicilio + "', "
				+ "'" + tipo + "', "
				+ "'" + celular + "', "
				+ "'" + telefono + "')";
		System.out.println(sql);
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.executeUpdate();
	}
	
	public void pagarCuota(int nro_grupo, int nro_cuota) throws Exception {
		Connection conn = connect();
		LocalDate today = LocalDate.now();
		String sql = "UPDATE cuota_familiar "
					+ "SET f_pago = '" + today.toString() + "' "
					+ "WHERE nro_grupo = " + nro_grupo + " "
					+ "AND nro_cuota = " + nro_cuota;
					
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.executeUpdate();
	}
}
