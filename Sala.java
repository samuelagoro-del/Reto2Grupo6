package Reto2Grupo6;

import java.util.ArrayList;

public class Sala {
	private String  sala;
	private int idsala;
	private ArrayList<Sesion> sesiones;

	public Sala(String nombre, int identificador) {
		this.sala = nombre;
		this.idsala = identificador;
		this.sesiones =  new ArrayList<>();



	}

	public void  añadirsesion(Sesion s  ) {
		sesiones.add(s);
	}

	public String getNombre() {
		return sala;
	}

	public int getIdentificador() {
		return idsala;
	}

	public ArrayList<Sesion> getSesiones() {
		return sesiones;
	}

	public String getSala() {
		return sala;
	}

	public void setSala(String sala) {
		this.sala = sala;
	}

	public int getIdsala() {
		return idsala;
	}

	public void setIdsala(int idsala) {
		this.idsala = idsala;
	}

	public void setSesiones(ArrayList<Sesion> sesiones) {
		this.sesiones = sesiones;
	}

	





}

