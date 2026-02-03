package Reto2Grupo6;

import java.util.ArrayList;

public class Sala {
    private String nombre;
    private int idSala;
    private ArrayList<Sesion> sesiones;

    public Sala(String nombre, int idSala) {
        this.nombre = nombre;
        this.idSala = idSala;
        this.sesiones = new ArrayList<>();
    }

    public Sala() {
        this.nombre = "";
        this.idSala = 0;
        this.sesiones = new ArrayList<>();
    }

    public void añadirSesion(Sesion s) { sesiones.add(s); }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getIdSala() { return idSala; }
    public void setIdSala(int idSala) { this.idSala = idSala; }

    public ArrayList<Sesion> getSesiones() { return sesiones; }
    public void setSesiones(ArrayList<Sesion> sesiones) { this.sesiones = sesiones; }
}
