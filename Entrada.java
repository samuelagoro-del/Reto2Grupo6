package Reto2Grupo6;

public class Entrada {
	private String identrada;
	private Sesion sesion;
	private int numeropersonas;
	private double precio;
	private float descuento;
	private Compra compra;
	public Entrada(String identificador, Sesion sesion, int numeropersonas, double precio, float descuento, Compra compra) {
		super();
		this.identrada = identificador;
		this.sesion = sesion;
		this.numeropersonas = numeropersonas;
		this.precio = precio;
		this.descuento = descuento;
		this.compra = compra;
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
		this.sesion = sesion;
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
		this.compra = compra;
	}

}
