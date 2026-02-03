package Reto2Grupo6;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BDconexion {
	private static final String URL =
		    "jdbc:mysql://localhost:33060/dam1_reto2_grupo6?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";

    private static final String USER = "root";
    private static final String PASS = "elorrieta";

    public static Connection getConexion() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

}
