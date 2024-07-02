package Clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class CConexion {
    private static CConexion instance;
    private Connection conectar = null;
    private String usuario = "usersql1";
    private String contraseña = "root";
    private String bd = "CuentasPorCobrar";
    private String ip = "localhost";
    private String puerto = "1433";
    private String cadena = "jdbc:sqlserver://" + ip + ":" + puerto + ";databaseName=" + bd + ";encrypt=true;trustServerCertificate=true;";

    private CConexion() {
        establecerConexion();
    }

    private void establecerConexion() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conectar = DriverManager.getConnection(cadena, usuario, contraseña);
            JOptionPane.showMessageDialog(null, "Conexión exitosa a la base de datos");
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar el driver de SQL Server: " + e.toString());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al conectar a la base de datos: " + e.toString());
        }
    }

    public void cerrarConexion() {
        try {
            if (conectar != null && !conectar.isClosed()) {
                conectar.close();
                JOptionPane.showMessageDialog(null, "Conexión cerrada correctamente");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cerrar la conexión: " + e.toString());
        }
    }

    public Connection getConexion() {
        return conectar;
    }

    public static synchronized CConexion getInstance() {
        if (instance == null) {
            instance = new CConexion();
        }
        return instance;
    }
}
