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

    // 1️⃣ Login o registro de usuario
    public static Cliente loginUsuario(Connection con, Scanner sc) throws SQLException {
        while (true) {
            System.out.println("1-Iniciar sesión:");
            System.out.println("2-Registrarse:");
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
                            System.out.println("Login correcto.\n");
                            return new Cliente(
                                    rs.getString("DNI"),
                                    rs.getString("nombre"),
                                    rs.getString("apellido"),
                                    rs.getString("correo"),
                                    rs.getString("contraseña")
                            );
                        } else {
                            System.out.println("DNI o contraseña incorrectos. Intenta de nuevo.\n");
                        }
                    }
                }

            } else if (opcion.equals("2")) {
                System.out.print("DNI: ");
                String dni = sc.nextLine();
                System.out.print("Nombre: ");
                String nombre = sc.nextLine();
                System.out.print("Apellido: ");
                String apellido = sc.nextLine();
                System.out.print("Correo: ");
                String correo = sc.nextLine();
                System.out.print("Contraseña: ");
                String contraseña = sc.nextLine();

                String sqlInsert = "INSERT INTO Cliente (DNI, nombre, apellido, correo, contraseña) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement ps = con.prepareStatement(sqlInsert)) {
                    ps.setString(1, dni);
                    ps.setString(2, nombre);
                    ps.setString(3, apellido);
                    ps.setString(4, correo);
                    ps.setString(5, contraseña);
                    ps.executeUpdate();
                    System.out.println("Usuario registrado correctamente.\n");
                    return new Cliente(dni, nombre, apellido, correo, contraseña);
                }
            } else {
                System.out.println("Opción inválida. Intenta de nuevo.\n");
            }
        }
    }

    // 2️⃣ Selección de película
    public static Pelicula seleccionarPelicula(Connection con, Scanner sc) throws SQLException {
        ArrayList<Pelicula> peliculas = new ArrayList<>();
        String sql = "SELECT * FROM Pelicula ORDER BY idPeli";
        try (PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
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

        int seleccion = 0;
        while (true) {
            System.out.print("Selecciona una película por número: ");
            seleccion = sc.nextInt();
            sc.nextLine();
            if (seleccion >= 1 && seleccion <= peliculas.size()) break;
            System.out.println("Opción inválida.");
        }
        return peliculas.get(seleccion - 1);
    }

    // 3️⃣ Selección de sesión (día → hora)
    public static Sesion seleccionarSesion(Connection con, Scanner sc, Pelicula pelicula) throws SQLException {
        ArrayList<LocalDate> dias = new ArrayList<>();
        ArrayList<Sesion> todasSesiones = new ArrayList<>();

        String sql = "SELECT s.*, sa.nombre AS nombreSala FROM Sesion s " +
                     "JOIN Sala sa ON s.idSala = sa.idSala " +
                     "WHERE s.idPeli=? ORDER BY s.fecha, s.horaIni";
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
                    todasSesiones.add(sesion);
                    if (!dias.contains(sesion.getFecha())) dias.add(sesion.getFecha());
                }
            }
        }

        // Mostrar días disponibles
        System.out.println("\nDías disponibles para " + pelicula.getTitulo() + ":");
        for (int i = 0; i < dias.size(); i++) {
            System.out.println((i + 1) + " - " + dias.get(i));
        }

        int diaSeleccionado = 0;
        while (true) {
            System.out.print("Selecciona un día por número: ");
            diaSeleccionado = sc.nextInt();
            sc.nextLine();
            if (diaSeleccionado >= 1 && diaSeleccionado <= dias.size()) break;
            System.out.println("Opción inválida.");
        }
        LocalDate dia = dias.get(diaSeleccionado - 1);

        // Mostrar horarios del día seleccionado
        ArrayList<Sesion> sesionesDelDia = new ArrayList<>();
        for (Sesion s : todasSesiones) {
            if (s.getFecha().equals(dia)) sesionesDelDia.add(s);
        }

        System.out.println("\nSesiones disponibles para el " + dia + ":");
        for (int i = 0; i < sesionesDelDia.size(); i++) {
            Sesion s = sesionesDelDia.get(i);
            System.out.println((i + 1) + " - " + s.getHoraInicio() + " - " + s.getHoraFin() +
                    " | Sala: " + s.getSala().getNombre() + " | Precio: " + String.format("%.2f", s.getPrecio()) + "€");
        }

        int sesionSeleccionada = 0;
        while (true) {
            System.out.print("Selecciona una sesión por número: ");
            sesionSeleccionada = sc.nextInt();
            sc.nextLine();
            if (sesionSeleccionada >= 1 && sesionSeleccionada <= sesionesDelDia.size()) break;
            System.out.println("Opción inválida.");
        }

        return sesionesDelDia.get(sesionSeleccionada - 1);
    }

    // 4️⃣ Registrar compra en BD y devolver idCompra
    public static int registrarCompra(Connection con, Cliente cliente, double total, float descuento) throws SQLException {
        String sql = "INSERT INTO Compra (fecha, hora, precio, descuento, DNI) VALUES (?, ?, ?, ?, ?)";
        int idCompra = 0;
        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            LocalDate fecha = LocalDate.now();
            LocalTime hora = LocalTime.now();
            ps.setDate(1, Date.valueOf(fecha));
            ps.setTime(2, Time.valueOf(hora));
            ps.setDouble(3, total);
            ps.setFloat(4, descuento);
            ps.setString(5, cliente.getDNI());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) idCompra = rs.getInt(1);
            }
        }
        return idCompra;
    }

    // 5️⃣ Registrar entradas en BD
    public static void registrarEntrada(Connection con, Entrada entrada, int idCompra) throws SQLException {
        String sql = "INSERT INTO Entrada (descuento, precio, num_pers, idCompra, idSesion) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setFloat(1, 0);
            ps.setDouble(2, entrada.getPrecio());
            ps.setInt(3, entrada.getNumeropersonas());
            ps.setInt(4, idCompra);
            ps.setInt(5, entrada.getSesion().getIdentificador());
            ps.executeUpdate();
        }
    }

    // 6️⃣ Guardar ticket en archivo de texto en carpeta 'tickets'
    public static void guardarTicket(Cliente cliente, ArrayList<Entrada> entradas, double total, float descuento, double totalFinal) {
        try {
            File carpeta = new File("src/Reto2Grupo6/tickets");
            if (!carpeta.exists()) carpeta.mkdir();

            String nombreArchivo = "src/Reto2Grupo6/tickets/ticket_" + cliente.getDNI() + "_" + System.currentTimeMillis() + ".txt";

            try (FileWriter fw = new FileWriter(nombreArchivo)) {
                fw.write("TICKET DE COMPRA\n");
                fw.write("Cliente: " + cliente.getNombre() + " " + cliente.getApellido() + " | DNI: " + cliente.getDNI() + "\n");
                fw.write("------------------------------------\n");
                for (Entrada e : entradas) {
                    fw.write(e + "\n");
                }
                fw.write("------------------------------------\n");
                fw.write("Total: " + String.format("%.2f", total) + "€\n");
                fw.write("Descuento aplicado: " + (descuento * 100) + "%\n");
                fw.write("Total a pagar: " + String.format("%.2f", totalFinal) + "€\n");
            }

            System.out.println("Ticket guardado correctamente en la carpeta 'tickets'.");
        } catch (IOException e) {
            System.out.println("Error al guardar el ticket.");
            e.printStackTrace();
        }
    }

    // ---------------- MAIN ----------------
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try (Connection con = BDconexion.getConexion()) {
            System.out.println("Bienvenido al sistema de venta de entradas del cine\n");

            Cliente cliente = loginUsuario(con, sc);

            boolean seguirComprando = true;
            ArrayList<Entrada> entradasSeleccionadas = new ArrayList<>();

            while (seguirComprando) {
                Pelicula pelicula = seleccionarPelicula(con, sc);
                Sesion sesion = seleccionarSesion(con, sc, pelicula);

                System.out.print("Número de espectadores: ");
                int numPers = sc.nextInt();
                sc.nextLine();

                double precioEntrada = sesion.getPrecio() * numPers;

                Entrada entrada = new Entrada();
                entrada.setSesion(sesion);
                entrada.setNumeropersonas(numPers);
                entrada.setPrecio(precioEntrada);
                entradasSeleccionadas.add(entrada);

                System.out.println("\nEntrada añadida: " + entrada);

                System.out.print("\n¿Desea agregar otra película? (s/n): ");
                String resp = sc.nextLine();
                if (!resp.equalsIgnoreCase("s")) seguirComprando = false;
            }

            // Calcular total y descuentos
            double total = 0;
            for (Entrada e : entradasSeleccionadas) total += e.getPrecio();

            float descuento = 0;
            if (entradasSeleccionadas.size() == 2) descuento = 0.20f;
            else if (entradasSeleccionadas.size() >= 3) descuento = 0.30f;

            double totalConDescuento = total * (1 - descuento);

            // Registrar compra
            int idCompra = registrarCompra(con, cliente, totalConDescuento, descuento);

            // Registrar entradas
            for (Entrada e : entradasSeleccionadas) {
                registrarEntrada(con, e, idCompra);
            }

            // Mostrar resumen
            System.out.println("\nResumen de compra:");
            for (Entrada e : entradasSeleccionadas) {
                System.out.println(e);
            }
            System.out.println("Total: " + String.format("%.2f", total) + "€");
            System.out.println("Descuento aplicado: " + (descuento * 100) + "%");
            System.out.println("Total a pagar: " + String.format("%.2f", totalConDescuento) + "€");

            // Guardar ticket
            guardarTicket(cliente, entradasSeleccionadas, total, descuento, totalConDescuento);

            System.out.println("\nGracias por su compra. Ticket guardado correctamente.");


        } catch (SQLException e) {
            e.printStackTrace();
        }

        sc.close();
    }
}
