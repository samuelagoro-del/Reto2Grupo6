package Reto2Grupo6;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Main1 {

    // ---------------- FUNCIONES ----------------

    // 1️⃣ Login o registro de usuario (CON CONTROL DE DNI DUPLICADO)
    public static Cliente loginUsuario(Connection con, Scanner sc) throws SQLException {
        while (true) {
            System.out.println("1 - Iniciar sesión");
            System.out.println("2 - Registrarse");
            System.out.print("Opción: ");
            String opcion = sc.nextLine();

            if (opcion.equals("1")) {
                System.out.print("DNI: ");
                String dni = sc.nextLine();
                System.out.print("Contraseña: ");
                String pass = sc.nextLine();

                String sql = "SELECT * FROM Cliente WHERE DNI=? AND contraseña=?";
                try (PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setString(1, dni);
                    ps.setString(2, pass);

                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            System.out.println("\nLogin correcto.\n");
                            return new Cliente(
                                    rs.getString("DNI"),
                                    rs.getString("nombre"),
                                    rs.getString("apellido"),
                                    rs.getString("correo"),
                                    rs.getString("contraseña")
                            );
                        } else {
                            System.out.println("DNI o contraseña incorrectos.\n");
                        }
                    }
                }

            } else if (opcion.equals("2")) {

                String dni, nombre, apellido, correo, contraseña;

                while (true) {
                    System.out.print("DNI: ");
                    dni = sc.nextLine();
                    System.out.print("Nombre: ");
                    nombre = sc.nextLine();
                    System.out.print("Apellido: ");
                    apellido = sc.nextLine();
                    System.out.print("Correo: ");
                    correo = sc.nextLine();
                    System.out.print("Contraseña: ");
                    contraseña = sc.nextLine();

                    String sqlInsert = "INSERT INTO Cliente VALUES (?, ?, ?, ?, ?)";

                    try (PreparedStatement ps = con.prepareStatement(sqlInsert)) {
                        ps.setString(1, dni);
                        ps.setString(2, nombre);
                        ps.setString(3, apellido);
                        ps.setString(4, correo);
                        ps.setString(5, contraseña);
                        ps.executeUpdate();

                        System.out.println("\nUsuario registrado correctamente.\n");
                        return new Cliente(dni, nombre, apellido, correo, contraseña);

                    } catch (SQLException ex) {
                        // Código típico de DNI duplicado en MySQL
                        if (ex.getErrorCode() == 1062) {
                            System.out.println("\n❌ ERROR: El DNI ya está registrado. Vuelve a intentarlo.\n");
                        } else {
                            throw ex;
                        }
                    }
                }

            } else {
                System.out.println("Opción inválida.\n");
            }
        }
    }

    // 2️⃣ Seleccionar película
    public static Pelicula seleccionarPelicula(Connection con, Scanner sc) throws SQLException {
        ArrayList<Pelicula> peliculas = new ArrayList<>();

        String sql = "SELECT * FROM Pelicula ORDER BY idPeli";
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                peliculas.add(new Pelicula(
                        rs.getInt("idPeli"),
                        rs.getString("titulo"),
                        rs.getInt("duracion"),
                        rs.getString("genero"),
                        rs.getDouble("precio")
                ));
            }
        }

        System.out.println("\nPelículas disponibles:");
        for (int i = 0; i < peliculas.size(); i++) {
            System.out.println((i + 1) + " - " + peliculas.get(i).getTitulo());
        }

        int opcion;
        do {
            System.out.print("Seleccione película: ");
            opcion = sc.nextInt();
            sc.nextLine();
        } while (opcion < 1 || opcion > peliculas.size());

        return peliculas.get(opcion - 1);
    }

    // 3️⃣ Seleccionar sesión
    public static Sesion seleccionarSesion(Connection con, Scanner sc, Pelicula pelicula) throws SQLException {
        ArrayList<Sesion> sesiones = new ArrayList<>();
        ArrayList<LocalDate> dias = new ArrayList<>();

        String sql = """
                SELECT s.*, sa.nombre AS nombreSala
                FROM Sesion s
                JOIN Sala sa ON s.idSala = sa.idSala
                WHERE s.idPeli=?
                ORDER BY s.fecha, s.horaIni
                """;

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, pelicula.getIdPelicula());

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Sala sala = new Sala(rs.getString("nombreSala"), rs.getInt("idSala"));
                    Sesion sesion = new Sesion(
                            rs.getInt("idSesion"),
                            rs.getDate("fecha").toLocalDate(),
                            rs.getTime("horaIni").toLocalTime(),
                            rs.getTime("horaFin").toLocalTime(),
                            rs.getDouble("precio"),
                            sala,
                            pelicula
                    );
                    sesiones.add(sesion);

                    if (!dias.contains(sesion.getFecha()))
                        dias.add(sesion.getFecha());
                }
            }
        }

        System.out.println("\nDías disponibles:");
        for (int i = 0; i < dias.size(); i++) {
            System.out.println((i + 1) + " - " + dias.get(i));
        }

        int diaSel;
        do {
            System.out.print("Seleccione día: ");
            diaSel = sc.nextInt();
            sc.nextLine();
        } while (diaSel < 1 || diaSel > dias.size());

        LocalDate dia = dias.get(diaSel - 1);

        ArrayList<Sesion> sesionesDia = new ArrayList<>();
        for (Sesion s : sesiones) {
            if (s.getFecha().equals(dia))
                sesionesDia.add(s);
        }

        System.out.println("\nSesiones:");
        for (int i = 0; i < sesionesDia.size(); i++) {
            Sesion s = sesionesDia.get(i);
            System.out.println((i + 1) + " - " + s.getHoraInicio() +
                    " | Sala " + s.getSala().getNombre() +
                    " | " + s.getPrecio() + "€");
        }

        int sesSel;
        do {
            System.out.print("Seleccione sesión: ");
            sesSel = sc.nextInt();
            sc.nextLine();
        } while (sesSel < 1 || sesSel > sesionesDia.size());

        return sesionesDia.get(sesSel - 1);
    }

    // 4️⃣ Registrar compra
    public static int registrarCompra(Connection con, Cliente cliente,
                                     double total, float descuento) throws SQLException {

        String sql = "INSERT INTO Compra (fecha, hora, precio, descuento, DNI) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setDate(1, Date.valueOf(LocalDate.now()));
            ps.setTime(2, Time.valueOf(LocalTime.now()));
            ps.setDouble(3, total);
            ps.setFloat(4, descuento);
            ps.setString(5, cliente.getDNI());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next())
                return rs.getInt(1);
        }
        return 0;
    }

    // 5️⃣ Registrar entrada
    public static void registrarEntrada(Connection con, Entrada e, int idCompra) throws SQLException {
        String sql = "INSERT INTO Entrada (descuento, precio, num_pers, idCompra, idSesion) " +
                     "VALUES (0, ?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDouble(1, e.getPrecio());
            ps.setInt(2, e.getNumeropersonas());
            ps.setInt(3, idCompra);
            ps.setInt(4, e.getSesion().getIdentificador());
            ps.executeUpdate();
        }
    }

    // 6️⃣ Guardar ticket
    public static void guardarTicket(Cliente c, ArrayList<Entrada> entradas,
                                     double total, float desc, double totalFinal) {

        try {
            File dir = new File("src/Reto2Grupo6/tickets");
            if (!dir.exists())
                dir.mkdir();

            String ruta = dir + "/ticket_" + c.getDNI() + "_" +
                          System.currentTimeMillis() + ".txt";

            try (FileWriter fw = new FileWriter(ruta)) {
                fw.write("TICKET DE COMPRA\n");
                fw.write("Cliente: " + c.getNombre() + " " + c.getApellido() + "\n");
                fw.write("---------------------------------\n");

                for (Entrada e : entradas)
                    fw.write(e + "\n");

                fw.write("---------------------------------\n");
                fw.write("Total: " + total + "€\n");
                fw.write("Descuento: " + (desc * 100) + "%\n");
                fw.write("Total a pagar: " + totalFinal + "€\n");
            }

            System.out.println("\nTicket guardado correctamente.");

        } catch (IOException e) {
            System.out.println("Error al guardar ticket.");
        }
    }

    // ---------------- MAIN ----------------
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try (Connection con = BDconexion.getConexion()) {

            System.out.println("🎬 Bienvenido al cine 🎬");

            ArrayList<Entrada> entradas = new ArrayList<>();
            boolean seguir = true;

            while (seguir) {
                Pelicula p = seleccionarPelicula(con, sc);
                Sesion s = seleccionarSesion(con, sc, p);

                System.out.print("Número de personas: ");
                int n = sc.nextInt();
                sc.nextLine();

                Entrada e = new Entrada();
                e.setSesion(s);
                e.setNumeropersonas(n);
                e.setPrecio(s.getPrecio() * n);
                entradas.add(e);

                System.out.print("¿Añadir otra película? (s/n): ");
                seguir = sc.nextLine().equalsIgnoreCase("s");
            }

            double total = 0;
            for (Entrada e : entradas)
                total += e.getPrecio();

            float descuento =
                    entradas.size() == 2 ? 0.20f :
                    entradas.size() >= 3 ? 0.30f : 0;

            double totalFinal = total * (1 - descuento);

            System.out.println("\nDebe iniciar sesión para finalizar la compra\n");
            Cliente cliente = loginUsuario(con, sc);

            int idCompra = registrarCompra(con, cliente, totalFinal, descuento);

            for (Entrada e : entradas)
                registrarEntrada(con, e, idCompra);

            guardarTicket(cliente, entradas, total, descuento, totalFinal);

            System.out.println("\nGracias por su compra.");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        sc.close();
    }
}
