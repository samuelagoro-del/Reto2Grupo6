package Reto2Grupo6;

public class Cliente {
	private Cliente DNI;
	private String nombre;
	private String apellido;
	private String gmail;
	private String contraseña;
	
	
	public Cliente(Cliente dNI, String nombre, String apellido, String gmail, String contraseña) {
		DNI = dNI;
		this.nombre = nombre;
		this.apellido = apellido;
		this.gmail = gmail;
		this.contraseña = contraseña;
	}


	public Cliente getDNI() {
		return DNI;
	}


	public void setDNI(Cliente dNI) {
		DNI = dNI;
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


	public String getGmail() {
		return gmail;
	}


	public void setGmail(String gmail) {
		this.gmail = gmail;
	}


	public String getContraseña() {
		return contraseña;
	}


	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	
	
}
