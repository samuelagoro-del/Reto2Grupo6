package Reto2Grupo6;

import java.time.LocalDate;
import java.time.LocalTime;

public class Sesion {
    private int idsesion;
    private LocalDate fecha;
    private LocalTime HoraInicio;
    private LocalTime HoraFin;
    private double precio;
    private Sala sala;
    private Pelicula pelicula;

    // Constructor
    public Sesion(int identificador, LocalDate fecha, LocalTime horainicio, LocalTime horafin,
                  double precio, Sala sala, Pelicula pelicula) {
        this.idsesion = identificador;
        this.fecha = fecha;
        this.HoraInicio = horainicio;
        this.HoraFin = horafin;
        this.precio = precio;
        this.sala = sala;
        this.pelicula = pelicula;
    }

    public Sesion() {}

    public int getIdentificador() {
        return idsesion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public LocalTime getHoraInicio() {
        return HoraInicio;
    }

    public LocalTime getHoraFin() {
        return HoraFin;
    }

    public double getPrecio() {
        return precio;
    }

    public Sala getSala() {
        return sala;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    @Override
    public String toString() {
        return pelicula.getTitulo() + " | " + fecha + " | " + HoraInicio + " - " + HoraFin +
               " | Sala: " + sala.getNombre() + " | Precio: " + precio + "€";
    }
}
