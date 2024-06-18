package com.mycompany.proyectobasedatos;

import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner entrada = new Scanner(System.in);
    public static void main(String[] args) {
        CConexion conexion = new CConexion();
        conexion.establecerConexcion();

        ClienteDAO clienteDAO = new ClienteDAO(conexion.getConexion());
        FacturaDAO facturaDAO = new FacturaDAO(conexion.getConexion());
        CajaBancoDAO cajaBancoDAO = new CajaBancoDAO(conexion.getConexion());
        MedioPagoDAO medioPagoDAO = new MedioPagoDAO(conexion.getConexion());

        try {
            // 1. Registro de clientes con línea de crédito
            Cliente nuevoCliente = new Cliente(1, "Juan Pérez", "Calle 123", "123456789", "juan@example.com", 1000.0);
            clienteDAO.insertarCliente(nuevoCliente);
            Cliente nuevoCliente1 = new Cliente(2, "Juan Pérez", "Calle 123", "123456789", "juan@example.com", 1000.0);
            clienteDAO.insertarCliente(nuevoCliente1);
            
            

            // 2. Registro de facturas de venta con formas de pago y fechas de vencimiento
            Factura nuevaFactura1 = new Factura(1, 1, "2024-06-16", 500.0, "Canjes", "2024-07-16", "Pendiente", 1);
            Factura nuevaFactura2 = new Factura(2, 1, "2024-06-16", 800.0, "Cheque", "2024-05-31", "Pendiente", 1);
            Factura nuevaFactura3 = new Factura(3, 1, "2024-06-16", 1200.0, "Transferencia", "2024-08-15", "Pendiente", 1);
            facturaDAO.insertarFactura(nuevaFactura1);
            facturaDAO.insertarFactura(nuevaFactura2);
            facturaDAO.insertarFactura(nuevaFactura3);

            // 3. Realizar pago de factura de venta con distintos medios de pago
            Cheque nuevoCheque = new Cheque(1, 1, 300.0, "2024-06-20", "123456", "Banco X");
            Transferencia nuevaTransferencia = new Transferencia(2, 2, 600.0, "2024-06-22", "789012", "Banco Y");
            Canje nuevoCanje = new Canje(3, 3, 1000.0, "2024-06-25", "Canje de documentos");
            medioPagoDAO.insertarMedioPago(nuevoCheque);
            medioPagoDAO.insertarMedioPago(nuevaTransferencia);
            medioPagoDAO.insertarMedioPago(nuevoCanje);

            // 4. Facturas por Cobrar a 30/60/90 Días
            System.out.println("Facturas por cobrar a 30 días:");
            imprimirFacturas(facturaDAO.obtenerFacturasPorPlazo(30));
            System.out.println("Facturas por cobrar a 60 días:");
            imprimirFacturas(facturaDAO.obtenerFacturasPorPlazo(60));
            System.out.println("Facturas por cobrar a 90 días:");
            imprimirFacturas(facturaDAO.obtenerFacturasPorPlazo(90));
            
            //mostrar facturas Vencidas(por castigar): (tabla) 1....
            facturaDAO.obtenerFacturasVencidas();
            facturaDAO.mostrarFacturasPorCastigar();
            // Castigo de facturas vencidas (introduces codigo,monto y cambia su estado a castigada y ademas verifica)
            System.out.print("\nDime el codigo de factura a castigar: ");
            int facturaId = entrada.nextInt();
            System.out.print("\nDime el monto de factura a castigar: ");
            int monto = entrada.nextInt();
            facturaDAO.castigarFactura(facturaId, monto);
            //muestra las facturas donde en su estado diga por castigar 2...... (reitera esto)
            facturaDAO.mostrarFacturasPorCastigar();
            
            //registro caja banco
            CajaBanco nuevoCaja = new CajaBanco(1, "Caja Banco X", 1000.0);
            cajaBancoDAO.insertarCajaBanco(nuevoCaja);
            
            // 6. Actualizar saldo de caja o banco
            cajaBancoDAO.actualizarSaldoCajaBanco(1, 6000.0);

            // 7. Obtener saldo actual de caja o banco
            double saldoActual = cajaBancoDAO.obtenerSaldoCajaBanco(1);
            System.out.println("Saldo actual de la caja o banco: " + saldoActual);

        } finally {
            conexion.cerrarConexion();
        }
    }


    private static void imprimirFacturas(List<Factura> facturas) {
        for (Factura factura : facturas) {
            System.out.println(factura);
        }
    }
}

