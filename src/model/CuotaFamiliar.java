package model;

public class CuotaFamiliar {
	private int nro_grupo;
	private int nro_cuota;
	private String periodo;
	private String fecha_pago;
	private float montoBase;
	private float montoTotal;
	
	public CuotaFamiliar() {
		
	}
	
	public CuotaFamiliar(int nro_grupo, int nro_cuota, String periodo, float montoBase, float montoTotal, String fecha_pago) {
		this.nro_grupo = nro_grupo;
		this.nro_cuota = nro_cuota;
		this.periodo = periodo;
		this.montoBase = montoBase;
		this.montoTotal = montoTotal;
		this.fecha_pago = fecha_pago;
	}

	public int getNro_grupo() {
		return nro_grupo;
	}

	public void setNro_grupo(int nro_grupo) {
		this.nro_grupo = nro_grupo;
	}

	public int getNro_cuota() {
		return nro_cuota;
	}

	public void setNro_cuota(int nro_cuota) {
		this.nro_cuota = nro_cuota;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public float getMontoBase() {
		return montoBase;
	}

	public void setMontoBase(float montoBase) {
		this.montoBase = montoBase;
	}

	public float getMontoTotal() {
		return montoTotal;
	}

	public void setMontoTotal(float montoTotal) {
		this.montoTotal = montoTotal;
	}
	
	public String getFechaPago() {
		return this.fecha_pago;
	}
	
	public void setFechaPago(String fecha_pago) {
		this.fecha_pago = fecha_pago;
	}
	
	@Override
	public String toString() {
		return this.periodo + ": $" + this.montoTotal + " [" + ((this.fecha_pago == null)? "NO PAGA" : "PAGA") + "]";
	}
}
