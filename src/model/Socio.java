package model;

public class Socio {
	private int nro_grupo;
	private int nro_socio;
	private String nombre;
	private String apellido;
	private String email;
	private String f_nac;
	private String domicilio;
	private Categoria tipo;
	private String celular;
	private String telefono;
	
	public Socio() {
		
	}

	public Socio(int nro_grupo, int nro_socio, String nombre, String apellido, String email, String f_nac,
			String domicilio, Categoria tipo, String celular, String telefono) {
		this.nro_grupo = nro_grupo;
		this.nro_socio = nro_socio;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.f_nac = f_nac;
		this.domicilio = domicilio;
		this.tipo = tipo;
		this.celular = celular;
		this.telefono = telefono;
	}
	
	public void updateSocio(String nombre, String apellido, String email, String f_nac,
			String domicilio, Categoria tipo, String celular, String telefono) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.f_nac = f_nac;
		this.domicilio = domicilio;
		this.tipo = tipo;
		this.celular = celular;
		this.telefono = telefono;
	}

	public int getNro_Grupo() {
		return nro_grupo;
	}

	public void setNro_Grupo(int nro_grupo) {
		this.nro_grupo = nro_grupo;
	}

	public int getNro_Socio() {
		return nro_socio;
	}

	public void setNro_Socio(int nro_socio) {
		this.nro_socio = nro_socio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getF_nac() {
		return f_nac;
	}

	public void setF_nac(String f_nac) {
		this.f_nac = f_nac;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public Categoria getTipo() {
		return tipo;
	}

	public void setTipo(Categoria tipo) {
		this.tipo = tipo;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	@Override
	public String toString() {
		return this.nro_grupo + this.nro_socio + ". " + this.nombre + " " + this.apellido + " [ " + this.getTipo().getTipo() + " ]";
	}
}
