package controller;

import java.util.ArrayList;
import java.util.Map;

import model.GrupoFamiliar;
import model.Socio;
import model.Categoria;
import model.CuotaFamiliar;

public interface IConnection {
	
	public ArrayList<GrupoFamiliar> getGruposFamiliares();
	
	public Socio getSocio(int nro_grupo, int nro_socio);
	
	public Map<String,Categoria> getTipos();
	
	public ArrayList<CuotaFamiliar> getCuotas(int nro_grupo);
}
