package com.mycompany.proyectobasedatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class CConexion {
    Connection conectar = null;
    String usuario = "usersql1";
    String contraseña = "root";
    String bd = "CuentasPorCobrar";
    String ip = "localhost";
    String puerto = "1433";
    
    String cadena = "jdbc:sqlserver://"+ip+":"+puerto+";databaseName="+bd+";encrypt=true;trustServerCertificate=true;";

    public Connection establecerConexcion() {
        try {
            // Cargar el driver de SQL Server
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            
            conectar = DriverManager.getConnection(cadena, usuario, contraseña);
            JOptionPane.showMessageDialog(null, "Se conectó correctamente a la base de datos");
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar el driver de SQL Server: " + e.toString());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al conectar a la base de datos: " + e.toString());
        }
        return conectar;
    }
    
    // Método para cerrar la conexión
    public void cerrarConexion() {
        try {
            if (conectar != null && !conectar.isClosed()) {
                conectar.close();
                JOptionPane.showMessageDialog(null, "Se cerró correctamente la conexión a la base de datos");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cerrar la conexión: " + e.toString());
        }
    }
    
    public Connection getConexion() {
        return conectar;
    }
    
}

