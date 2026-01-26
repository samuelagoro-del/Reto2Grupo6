package Reto2Grupo6;

public class Entrada {
	private String identrada;
	private Sesion sesion;
	private int numeropersonas;
	private double precio;
	private float descuento;
	private Compra compra;
	
	
	public Entrada(String identificador, Sesion sesion, int numeropersonas, double precio, float descuento, Compra compra) {
		this.identrada = identificador;
		this.sesion = new Sesion();
		this.numeropersonas = numeropersonas;
		this.precio = precio;
		this.descuento = descuento;
		this.compra = new Compra();
	}
	
	public Entrada() {
		this.identrada = "";
		this.sesion = new Sesion();
		this.numeropersonas = 0;
		this.precio = 0;
		this.descuento = 0;
		this.compra = new Compra();
	}
	
	
	public String getIdentificador() {
		return identrada;
	}
	
	public void setIdentificador(String identificador) {
		this.identrada = identificador;
	}
	
	public Sesion getSesion() {
		return sesion;
	}
	
	public void setSesion(Sesion sesion) {
		this.sesion = new Sesion();
	}
	
	public int getNumeropersonas() {
		return numeropersonas;
	}
	
	public void setNumeropersonas(int numeropersonas) {
		this.numeropersonas = numeropersonas;
	}
	
	public double getPrecio() {
		return precio;
	}
	
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	public float getDescuento() {
		return descuento;
	}
	
	public void setDescuento(float descuento) {
		this.descuento = descuento;
	}
	
	public Compra getCompra() {
		return compra;
	}
	
	public void setCompra(Compra compra) {
		this.compra = new Compra();
	}

}
