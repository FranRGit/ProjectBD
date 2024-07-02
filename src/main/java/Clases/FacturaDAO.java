package Clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class FacturaDAO {
    private Connection conexion;

    public FacturaDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public void insertarFactura(Factura factura) {
        String sql = "INSERT INTO Facturas (clienteId, fecha, monto, formaPago, fechaVencimiento, estado, cajaBancoId) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, factura.getClienteId());
            ps.setString(2, factura.getFecha());
            ps.setDouble(3, factura.getMonto());
            ps.setString(4, factura.getFormaPago());
            ps.setString(5, factura.getFechaVencimiento());
            ps.setString(6, factura.getEstado());
            ps.setInt(7, factura.getCajaBancoId());

            int resultado = ps.executeUpdate();
            if (resultado > 0) {
                JOptionPane.showMessageDialog(null, "Factura insertada correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo insertar la factura.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al insertar factura: " + ex.getMessage());
        }
    }
    
    //aun no estoy seguro
    public List<Factura> obtenerFacturasPorPlazo(int plazoDias) {
        List<Factura> facturas = new ArrayList<>();
        LocalDate fechaHoy = LocalDate.now();
        LocalDate fechaLimite = fechaHoy.plusDays(plazoDias);

        String sql = "SELECT * FROM Facturas WHERE fechaVencimiento <= ? AND estado = 'Pendiente'";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, fechaLimite.toString());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int facturaId = rs.getInt("facturaId");
                int clienteId = rs.getInt("clienteId");
                String fecha = rs.getString("fecha");
                double monto = rs.getDouble("monto");
                String formaPago = rs.getString("formaPago");
                String fechaVencimiento = rs.getString("fechaVencimiento");
                String estado = rs.getString("estado");
                int cajaBancoId = rs.getInt("cajaBancoId");

                Factura factura = new Factura(facturaId, clienteId, fecha, monto, formaPago, fechaVencimiento, estado, cajaBancoId);
                facturas.add(factura);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al obtener facturas por plazo: " + ex.getMessage());
        }

        return facturas;
    }
    

    public List<Factura> obtenerFacturasVencidas() {
        List<Factura> facturasVencidas = new ArrayList<>();
        LocalDate fechaHoy = LocalDate.now();

        String sql = "SELECT * FROM Facturas WHERE fechaVencimiento < ? AND estado = 'Pendiente'";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, fechaHoy.toString());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int facturaId = rs.getInt("facturaId");
                int clienteId = rs.getInt("clienteId");
                String fecha = rs.getString("fecha");
                double monto = rs.getDouble("monto");
                String formaPago = rs.getString("formaPago");
                String fechaVencimiento = rs.getString("fechaVencimiento");
                int cajaBancoId = rs.getInt("cajaBancoId");

                // Actualizar estado de la factura a "Por Castigar"
                actualizarEstadoFactura(facturaId, "Por Castigar");

                Factura factura = new Factura(facturaId, clienteId, fecha, monto, formaPago, fechaVencimiento, "Por Castigar", cajaBancoId);
                facturasVencidas.add(factura);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al obtener facturas vencidas: " + ex.getMessage());
        }

        System.out.println("Facturas vencidas obtenidas y marcadas como Por Castigar: " + facturasVencidas.size());

        return facturasVencidas;
    }

    private void actualizarEstadoFactura(int facturaId, String nuevoEstado) {
        String sql = "UPDATE Facturas SET estado = ? WHERE facturaId = ?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, nuevoEstado);
            ps.setInt(2, facturaId);

            int resultado = ps.executeUpdate();
            if (resultado > 0) {
                System.out.println("Estado de la factura actualizado a " + nuevoEstado + " correctamente.");
            } else {
                System.out.println("No se pudo actualizar el estado de la factura.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al actualizar estado de la factura: " + ex.getMessage());
        }
    }
    
    
    public void castigarFacturaConMonto(int facturaId, double montoAumento) {
        System.out.println("Castigando.....");
        String sql = "UPDATE Facturas SET estado = 'Castigada', monto = monto + ? WHERE facturaId = ?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setDouble(1, montoAumento);
            ps.setInt(2, facturaId);

            int resultado = ps.executeUpdate();
            if (resultado > 0) {
                JOptionPane.showMessageDialog(null, "Factura castigada correctamente y monto aumentado.");
                
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo castigar la factura.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al castigar factura: " + ex.getMessage());
        }
    }
    
    public void mostrarFacturasPorCastigar() {
        List<Factura> facturasPorCastigar = obtenerFacturasPorEstado("Por Castigar");

        System.out.println("Facturas por castigar:");
        for (Factura factura : facturasPorCastigar) {
            System.out.println(factura);
        }
    }

    public void castigarFactura(int facturaId, double montoAumento) {
        Factura factura = obtenerFacturaPorId(facturaId);

        if (factura != null && factura.getEstado().equals("Por Castigar")) {
            castigarFacturaConMonto(facturaId, montoAumento);
        } else {
            System.out.println("Factura no encontrada o no se puede castigar.");
        }
    }

    private Factura obtenerFacturaPorId(int facturaId) {
        Factura factura = null;
        String sql = "SELECT * FROM Facturas WHERE facturaId = ?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, facturaId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int clienteId = rs.getInt("clienteId");
                String fecha = rs.getString("fecha");
                double monto = rs.getDouble("monto");
                String formaPago = rs.getString("formaPago");
                String fechaVencimiento = rs.getString("fechaVencimiento");
                String estado = rs.getString("estado");
                int cajaBancoId = rs.getInt("cajaBancoId");

                factura = new Factura(facturaId, clienteId, fecha, monto, formaPago, fechaVencimiento, estado, cajaBancoId);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al obtener factura por ID: " + ex.getMessage());
        }

        return factura;
    }
    
    
    private List<Factura> obtenerFacturasPorEstado(String estado) {
        List<Factura> facturas = new ArrayList<>();
        String sql = "SELECT * FROM Facturas WHERE estado = ?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, estado);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int facturaId = rs.getInt("facturaId");
                int clienteId = rs.getInt("clienteId");
                String fecha = rs.getString("fecha");
                double monto = rs.getDouble("monto");
                String formaPago = rs.getString("formaPago");
                String fechaVencimiento = rs.getString("fechaVencimiento");
                int cajaBancoId = rs.getInt("cajaBancoId");

                Factura factura = new Factura(facturaId, clienteId, fecha, monto, formaPago, fechaVencimiento, estado, cajaBancoId);
                facturas.add(factura);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al obtener facturas por estado: " + ex.getMessage());
        }

        return facturas;
    }
}

