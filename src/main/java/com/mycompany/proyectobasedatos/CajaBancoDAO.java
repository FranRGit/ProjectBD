package com.mycompany.proyectobasedatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class CajaBancoDAO {
    private Connection conexion;

    public CajaBancoDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public void insertarCajaBanco(CajaBanco cajaBanco) {
        String sql = "INSERT INTO CajaBanco (nombre, saldo) " +
                     "VALUES (?, ?)";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, cajaBanco.getNombre());
            ps.setDouble(2, cajaBanco.getSaldo());

            int resultado = ps.executeUpdate();
            if (resultado > 0) {
                JOptionPane.showMessageDialog(null, "Caja o banco insertado correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo insertar la caja o banco.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al insertar caja o banco: " + ex.getMessage());
        }
    }

    public void actualizarSaldoCajaBanco(int cajaBancoId, double nuevoSaldo) {
        String sql = "UPDATE CajaBanco SET saldo = ? WHERE cajaBancoId = ?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setDouble(1, nuevoSaldo);
            ps.setInt(2, cajaBancoId);

            int resultado = ps.executeUpdate();
            if (resultado > 0) {
                JOptionPane.showMessageDialog(null, "Saldo actualizado correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo actualizar el saldo.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar saldo: " + ex.getMessage());
        }
    }

    public double obtenerSaldoCajaBanco(int cajaBancoId) {
        String sql = "SELECT saldo FROM CajaBanco WHERE cajaBancoId = ?";
        double saldo = 0;

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, cajaBancoId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                saldo = rs.getDouble("saldo");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al obtener saldo: " + ex.getMessage());
        }

        return saldo;
    }
    

}
