package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

public class GrupoFamiliar {
	private int nro_grupo;
	private Socio titular;
	private ArrayList<Socio> socios;
	private ArrayList<CuotaFamiliar> cuotas;
	
	public GrupoFamiliar() {
		this.socios = new ArrayList<Socio>();
		this.cuotas = new ArrayList<CuotaFamiliar>();
	}
	
	public GrupoFamiliar(int nro_grupo, Socio titular) {
		this.socios = new ArrayList<Socio>();
		this.cuotas = new ArrayList<CuotaFamiliar>();
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
	
	public void setNro_Grupo(int nro_grupo) {
		this.nro_grupo = nro_grupo;
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
	
	public void pagarCuota(int nro_cuota) {
		Iterator<CuotaFamiliar> it = this.cuotas.iterator();
		while(it.hasNext()) {
			CuotaFamiliar cf = it.next();
			if(cf.getNro_cuota() == nro_cuota && cf.getFechaPago() == null) {
				LocalDate today = LocalDate.now();
				cf.setFechaPago(today.toString());
			}
		}
	}
}
