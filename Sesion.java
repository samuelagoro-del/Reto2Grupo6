package Reto2Grupo6;

import java.time.LocalDate;
import java.time.LocalTime;

public class Sesion {
	private int idsesion;
	private LocalDate fecha;
	private LocalTime horainicio;
	private LocalTime horafin;
	public double precio;
	private Sala sala;
	private Pelicula pelicula;

	public Sesion(int identificador, LocalDate fecha, LocalTime horainicio, LocalTime horafin, double precio, Sala sala, Pelicula pelicula) {
		this.idsesion = identificador;
		this.fecha = fecha;
		this.horainicio = horainicio;
		this.horafin = horafin;
		this.precio = precio;
		this.sala = new Sala();
		this.pelicula = new Pelicula();
	}
	
	public Sesion() {
		this.idsesion = 0;
		this.fecha = "";
		this.horainicio = "";
		this.horafin = "";
		this.precio = 0;
		this.sala = new Sala();
		this.pelicula = new Pelicula();
	}

	public int getIdentificador() {
		return idsesion;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public LocalTime getHorainicio() {
		return horainicio;
	}

	public LocalTime getHorafin() {
		return horafin;
	}

	public Sala getSala() {
		return sala;
	}

	public Pelicula getPelicula() {
		return pelicula;
	}
	

	

}
