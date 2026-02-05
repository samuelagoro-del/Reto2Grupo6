package Reto2Grupo6;

import java.time.LocalDate;
import java.time.LocalTime;

public class Compra {
    private int idCompra; // int porque es auto_increment en la BD
    private LocalDate fecha;
    private LocalTime hora;
    private double precio;
    private float descuento;
    private Cliente cliente;

    public Compra(int idCompra, LocalDate fecha, LocalTime hora, double precio, float descuento, Cliente cliente) {
        this.idCompra = idCompra;
        this.fecha = fecha;
        this.hora = hora;
        this.precio = precio;
        this.descuento = descuento;
        this.cliente = cliente;
    }

    public Compra() {}

    public int getIdCompra() { return idCompra; }
    public void setIdCompra(int idCompra) { this.idCompra = idCompra; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public LocalTime getHora() { return hora; }
    public void setHora(LocalTime hora) { this.hora = hora; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public float getDescuento() { return descuento; }
    public void setDescuento(float descuento) { this.descuento = descuento; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
}
