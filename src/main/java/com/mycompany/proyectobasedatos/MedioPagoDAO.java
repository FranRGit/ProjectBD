package com.mycompany.proyectobasedatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class MedioPagoDAO {
    private Connection conexion;


    public MedioPagoDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public void insertarMedioPago(MedioPago medioPago) {
        String sql = "";
        int medioPagoId = -1; // Variable para almacenar el ID generado del medio de pago

        // Determinar la tabla destino según el tipo de medio de pago
        if (medioPago instanceof Cheque) {
            sql = "INSERT INTO Cheques (facturaId, monto, fecha, numeroCheque, banco) " +
                  "VALUES (?, ?, ?, ?, ?)";
        } else if (medioPago instanceof Transferencia) {
            sql = "INSERT INTO Transferencias (facturaId, monto, fecha, numeroCuenta, banco) " +
                  "VALUES (?, ?, ?, ?, ?)";
        } else if (medioPago instanceof Canje) {
            sql = "INSERT INTO Canjes (facturaId, monto, fecha, descripcion) " +
                  "VALUES (?, ?, ?, ?)";
        }

        try (PreparedStatement ps = conexion.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, medioPago.getFacturaId());
            ps.setDouble(2, medioPago.getMonto());
            ps.setString(3, medioPago.getFecha());

            if (medioPago instanceof Cheque) {
                Cheque cheque = (Cheque) medioPago;
                ps.setString(4, cheque.getNumeroCheque());
                ps.setString(5, cheque.getBanco());
            } else if (medioPago instanceof Transferencia) {
                Transferencia transferencia = (Transferencia) medioPago;
                ps.setString(4, transferencia.getNumeroCuenta());
                ps.setString(5, transferencia.getBanco());
            } else if (medioPago instanceof Canje) {
                Canje canje = (Canje) medioPago;
                ps.setString(4, canje.getDescripcion());
            }

            int resultado = ps.executeUpdate();
            if (resultado > 0) {
                // Obtener el ID generado del medio de pago
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    medioPagoId = generatedKeys.getInt(1);
                }

                // Insertar en la tabla Pagos si es necesario
                if (medioPagoId != -1) {
                    // Lógica para insertar en la tabla Pagos
                    insertarPago(medioPagoId, medioPago);
                }

                JOptionPane.showMessageDialog(null, "Medio de pago insertado correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo insertar el medio de pago.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al insertar medio de pago: " + ex.getMessage());
        }
    }

    private void insertarPago(int medioPagoId, MedioPago medioPago) throws SQLException {
        String sql = "INSERT INTO Pagos (FacturaID, Monto, Fecha, MedioPago, Detalles) " +
                     "VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, medioPago.getFacturaId());
            ps.setDouble(2, medioPago.getMonto());
            ps.setString(3, medioPago.getFecha());
            ps.setString(4, medioPago.getClass().getSimpleName()); // Ejemplo: Cheque, Transferencia, Canje
            ps.setString(5, obtenerDetallesMedioPago(medioPago)); // Método para obtener detalles específicos del medio de pago

            int resultado = ps.executeUpdate();
            if (resultado <= 0) {
                throw new SQLException("Error al insertar en la tabla Pagos.");
            }
        }
    }

    private String obtenerDetallesMedioPago(MedioPago medioPago) {
        if (medioPago instanceof Cheque) {
            Cheque cheque = (Cheque) medioPago;
            return "Número de Cheque: " + cheque.getNumeroCheque() + ", Banco: " + cheque.getBanco();
        } else if (medioPago instanceof Transferencia) {
            Transferencia transferencia = (Transferencia) medioPago;
            return "Número de Cuenta: " + transferencia.getNumeroCuenta() + ", Banco: " + transferencia.getBanco();
        } else if (medioPago instanceof Canje) {
            Canje canje = (Canje) medioPago;
            return "Descripción: " + canje.getDescripcion();
        }
        return "";
    }



}
