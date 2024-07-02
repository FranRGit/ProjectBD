package Clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ClienteDAO {
    private Connection conexion;

    public ClienteDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public void insertarCliente(Cliente cliente) {
        String sql = "INSERT INTO Clientes (nombre, direccion, telefono, email, lineaCredito) " +
                     "VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getDireccion());
            ps.setString(3, cliente.getTelefono());
            ps.setString(4, cliente.getEmail());
            ps.setDouble(5, cliente.getLineaCredito());

            int resultado = ps.executeUpdate();
            if (resultado > 0) {
                JOptionPane.showMessageDialog(null, "Cliente insertado correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo insertar el cliente.");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al insertar cliente: " + ex.getMessage());
        }
    }
    
    public void actualizarLineaCredito(int clienteId, double nuevaLineaCredito) {
            String sql = "UPDATE Clientes SET lineaCredito = ? WHERE clienteId = ?";

            try (PreparedStatement ps = conexion.prepareStatement(sql)) {
                ps.setDouble(1, nuevaLineaCredito);
                ps.setInt(2, clienteId);

                int resultado = ps.executeUpdate();
                if (resultado > 0) {
                    JOptionPane.showMessageDialog(null, "Línea de crédito actualizada correctamente.");
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo actualizar la línea de crédito.");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al actualizar línea de crédito: " + ex.getMessage());
            }
        }
    
    
    
    public ArrayList obtenerListaClientes(){
         ArrayList<Cliente> clientes = new ArrayList<>();
        
         try {
            PreparedStatement ps = conexion.prepareStatement("SELECT * FROM Clientes");
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Cliente aux = new Cliente();
                
                aux.setClienteId(rs.getInt("ClienteID"));
                aux.setNombre(rs.getString("Nombre"));
                aux.setDireccion(rs.getString("Direccion"));
                aux.setTelefono(rs.getString("Telefono"));
                aux.setEmail(rs.getString("Email"));
                aux.setLineaCredito(rs.getDouble("LineaCredito"));

                clientes.add(aux);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
         return clientes;
    }
}

