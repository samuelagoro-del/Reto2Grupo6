package Reto2Grupo6;

public class Cliente {
    private String DNI;
    private String nombre;
    private String apellido;
    private String correo;
    private String contraseña;

    public Cliente(String DNI, String nombre, String apellido, String correo, String contrasena) {
        this.DNI = DNI;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.contraseña = contrasena;
    }

    public Cliente() {
        this.DNI = "";
        this.nombre = "";
        this.apellido = "";
        this.correo = "";
        this.contraseña = "";
    }

    public String getDNI() { return DNI; }
    public void setDNI(String DNI) { this.DNI = DNI; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getContrasena() { return contraseña; }
    public void setContrasena(String contrasena) { this.contraseña = contrasena; }
}
