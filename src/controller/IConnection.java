package controller;

import java.util.ArrayList;
import java.util.Map;

import model.Categoria;
import model.CuotaFamiliar;
import model.GrupoFamiliar;
import model.Socio;

public interface IConnection {
	
	public ArrayList<GrupoFamiliar> getGruposFamiliares() throws Exception;
	
	public Socio getSocio(int nro_grupo, int nro_socio) throws Exception;
	
	public Map<String,Categoria> getTipos() throws Exception;
	
	public ArrayList<CuotaFamiliar> getCuotas(int nro_grupo) throws Exception;
	
	public void addSocio(int nro_grupo, int nro_socio, String nombre, String apellido, String email, String f_nac,
			String domicilio, String tipo, String celular, String telefono) throws Exception;
	
	public void updateSocio(int nro_grupo, int nro_socio, String nombre, String apellido, String email, String f_nac,
			String domicilio, String tipo, String celular, String telefono) throws Exception;
	
	public void addGrupo(String nombre, String apellido, String email, String f_nac,
			String domicilio, String tipo, String celular, String telefono) throws Exception;
	
	public void pagarCuota(int nro_grupo, int nro_cuota) throws Exception;
}
