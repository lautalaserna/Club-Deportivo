package model;

import java.util.ArrayList;
import java.util.Iterator;

public class GrupoFamiliar {
	private int nro_grupo;
	private Socio titular;
	private ArrayList<Socio> socios;
	private ArrayList<CuotaFamiliar> cuotas;
	
	public GrupoFamiliar() {
		
	}
	
	public GrupoFamiliar(int nro_grupo, Socio titular) {
		this.socios = new ArrayList<Socio>();
		this.nro_grupo = nro_grupo;
		this.titular = titular;
	}
	
	public void addSocio(Socio s) {
		this.socios.add(s);
	}
	
	@Override
	public String toString() {
		return "Grupo " + this.nro_grupo + ": " + this.titular.getNombre() + " " + this.titular.getApellido();
	}
	
	public int getNro_Grupo() {
		return this.nro_grupo;
	}
	
	public ArrayList<Socio> getSocios(){
		return this.socios;
	}
	
	public Socio getTitular() {
		return this.titular;
	}

	public void setTitular(Socio titular) {
		this.titular = titular;
	}
	
	public ArrayList<CuotaFamiliar> getCuotas(){
		return this.cuotas;
	}
	
	public void setCuotas(ArrayList<CuotaFamiliar> cuotas) {
		this.cuotas = cuotas;
	}
	
	public float getDeudaTotal() {
		float deuda = 0;
		Iterator<CuotaFamiliar> it = this.cuotas.iterator();
		while(it.hasNext()) {
			CuotaFamiliar cf = it.next();
			if(cf.getFechaPago() == null) {
				deuda += cf.getMontoTotal();
			}
		}
		return deuda;
	}
}
