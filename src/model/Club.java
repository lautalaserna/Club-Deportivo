package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import controller.Controller;

public class Club {
	private static Club instance = null;
	private ArrayList<GrupoFamiliar> grupos = new ArrayList<GrupoFamiliar>();
	private Map<String,Categoria> tipos = new HashMap<String,Categoria>();
	
	private Club() {
	
	}
	
	public static Club getInstance() {
		if (instance == null) {
			instance = new Club();
		}
		return instance;
	}
	
	public static void main(String[] args) {
		Club c = Club.getInstance();
		new Controller();
	}
	
	public ArrayList<GrupoFamiliar> getGrupos(){
		return this.grupos;
	}
	
	public void setGrupos(ArrayList<GrupoFamiliar> grupos) {
		this.grupos = grupos;
	}
	
	public void addGrupo(GrupoFamiliar g) {
		this.grupos.add(g);
	}
	
	public Map<String,Categoria> getTipos(){
		return this.tipos;
	}
	
	public void setTipos(Map<String,Categoria> tipos) {
		this.tipos = tipos;
	}
	
	public GrupoFamiliar getGrupo(int nro_grupo) {
		Iterator<GrupoFamiliar> it = this.grupos.iterator();
		while(it.hasNext()) {
			GrupoFamiliar gf = (GrupoFamiliar) it.next();
			if(gf.getNro_Grupo() == nro_grupo) {
				return gf;
			}
		}
		return null;
	}
}
