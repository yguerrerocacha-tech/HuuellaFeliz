package _01_conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    

    private static final String URL = "jdbc:sqlserver://localhost\\MSSQLSERVER;databaseName=HuellaFeliz;user=sa;password=1234;encrypt=false;trustServerCertificate=true;";
    public static Connection getConexion() {
        Connection cn = null;
        try {
            Class.forName(DRIVER);
            cn = DriverManager.getConnection(URL);
            System.out.println("¡Conexión exitosa a SQL Server con Autenticación de Windows!");
        } catch (ClassNotFoundException e) {
            System.out.println("Error: No se encontró el Driver de SQL Server -> " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error de SQL en Conexión -> " + e.getMessage());
        }
        return cn;
    }
    
    public static void main(String[] args) {
        Conexion.getConexion();
    }
}