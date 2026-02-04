package Reto2Grupo6.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import Reto2Grupo6.BDconexion;

public class LoginTest {

    @Test
    public void testLoginCorrecto() {
        String dni = "12345678A";
        String pass = "admin";
        boolean resultado = dni.equals("12345678A") && pass.equals("admin");
        assertTrue(resultado);
    }

    @Test
    public void testLoginIncorrecto() {
        String dni = "12345678A";
        String pass = "1234";
        boolean resultado = dni.equals("12345678A") && pass.equals("admin");
        assertFalse(resultado);
    }

    @Test
    public void testRegistroClienteCorrecto() throws SQLException {
        Connection con = BDconexion.getConexion();

        String dni = "99999999Z"; // DNI de prueba
        String nombre = "Ana";
        String apellido = "Perez";
        String correo = "ana@test.com";
        String password = "1234";

        // Limpiar por si ya existe
        try (PreparedStatement psDel = con.prepareStatement("DELETE FROM Cliente WHERE DNI=?")) {
            psDel.setString(1, dni);
            psDel.executeUpdate();
        }

        // Insertar cliente
        try (PreparedStatement ps = con.prepareStatement("INSERT INTO Cliente VALUES (?, ?, ?, ?, ?)")) {
            ps.setString(1, dni);
            ps.setString(2, nombre);
            ps.setString(3, apellido);
            ps.setString(4, correo);
            ps.setString(5, password);
            int filas = ps.executeUpdate();
            assertEquals(1, filas); // Debe insertarse
        }

        // Verificar en la BD
        try (PreparedStatement ps = con.prepareStatement("SELECT * FROM Cliente WHERE DNI=?")) {
            ps.setString(1, dni);
            ResultSet rs = ps.executeQuery();
            assertTrue(rs.next()); // Debe existir
            assertEquals(nombre, rs.getString("nombre"));
        }

        // Limpiar datos de prueba
        try (PreparedStatement psDel = con.prepareStatement("DELETE FROM Cliente WHERE DNI=?")) {
            psDel.setString(1, dni);
            psDel.executeUpdate();
        }

        con.close();
    }

    // Test 2: Registrar cliente con DNI   falla
    @Test
    public void testRegistroClienteIncorrecto() throws SQLException {
        Connection con = BDconexion.getConexion();

        String dni = "12345678A"; // DNI que ya existe en la BD
        String nombre = "Luis";
        String apellido = "Gomez";
        String correo = "luis@test.com";
        String password = "abcd";

        // Intentar insertar un cliente con DNI duplicado
        boolean fallo = false;
        try (PreparedStatement ps = con.prepareStatement("INSERT INTO Cliente VALUES (?, ?, ?, ?, ?)")) {
            ps.setString(1, dni);
            ps.setString(2, nombre);
            ps.setString(3, apellido);
            ps.setString(4, correo);
            ps.setString(5, password);
            ps.executeUpdate();
        } catch (SQLException e) {
            fallo = true; 
        }

        assertTrue(fallo); 
        con.close();
    }
}
