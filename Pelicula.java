package Reto2Grupo6;

public class Pelicula {
    private int idPelicula;
    private String titulo;
    private int duracion;
    private String genero;
    private double precio;

    public Pelicula(int idPelicula, String titulo, int duracion, String genero, double precio) {
        this.idPelicula = idPelicula;
        this.titulo = titulo;
        this.duracion = duracion;
        this.genero = genero;
        this.precio = precio;
    }

    public Pelicula(int idPelicula, String titulo) {
        this.idPelicula = idPelicula;
        this.titulo = titulo;
    }

    public int getIdPelicula() { return idPelicula; }
    public void setIdPelicula(int idPelicula) { this.idPelicula = idPelicula; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public int getDuracion() { return duracion; }
    public void setDuracion(int duracion) { this.duracion = duracion; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    @Override
    public String toString() {
        return titulo;
    }
}
