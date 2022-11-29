package model;

public class Categoria {
	private String tipo;
	private float porcentaje;
	
	public Categoria(String tipo, float porcentaje) {
		this.tipo = tipo;
		this.porcentaje = porcentaje;
	}
	
	public String getTipo() {
		return this.tipo;
	}

	public float getPorcentaje() {
		return this.porcentaje;
	}

}
