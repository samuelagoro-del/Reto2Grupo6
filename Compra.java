package Reto2Grupo6;

import java.time.LocalDate;
import java.time.LocalTime;

public class Compra {
	private String idcompra;
	private LocalDate fecha;
	private LocalTime hora;
	private double precio;
	private float descuento;
	private Cliente cliente;
	
	
	public Compra(String idcompra, LocalDate fecha, LocalTime hora, double precio, float descuento, Cliente cliente) {
		this.idcompra = idcompra;
		this.fecha = fecha;
		this.hora = hora;
		this.precio = precio;
		this.descuento = descuento;
		this.cliente = new Cliente();
	}

	public Compra() {
		this.idcompra = "";
		this.fecha = "";
		this.hora = "";
		this.precio = 0;
		this.descuento = 0;
		this.cliente = new Cliente();
	}


	
	public String getIdcompra() {
		return idcompra;
	}


	public void setIdcompra(String idcompra) {
		this.idcompra = idcompra;
	}


	public LocalDate getFecha() {
		return fecha;
	}


	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}


	public LocalTime getHora() {
		return hora;
	}


	public void setHora(LocalTime hora) {
		this.hora = hora;
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


	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = new Cliente();
	}
 
}
