
package Clases;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class Factura {
    private int facturaId;
    private int clienteId;
    private int cajaBancoId;
    private String fecha;
    private double monto;
    private String formaPago;
    private String fechaVencimiento;
    private String estado;

    public Factura(int facturaId, int clienteId, String fecha, double monto, String formaPago, String fechaVencimiento, String estado, int cajaBancoId) {
        this.facturaId = facturaId;
        this.clienteId = clienteId;
        this.cajaBancoId = cajaBancoId;
        this.fecha = fecha;
        this.monto = monto;
        this.formaPago = formaPago;
        this.fechaVencimiento = fechaVencimiento;
        this.estado = estado;
    }

    public int getFacturaId() {
        return facturaId;
    }

    public void setFacturaId(int facturaId) {
        this.facturaId = facturaId;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getCajaBancoId() {
        return cajaBancoId;
    }

    public void setCajaBancoId(int cajaBancoId) {
        this.cajaBancoId = cajaBancoId;
    }
    
    
    public int getPlazoDias() {
        LocalDate fechaEmision = LocalDate.parse(fecha);
        LocalDate fechaVenc = LocalDate.parse(fechaVencimiento);
        return (int) ChronoUnit.DAYS.between(fechaEmision, fechaVenc);
    }

    @Override
    public String toString() {
        return "Factura{" +
               "facturaId=" + facturaId +
               ", clienteId=" + clienteId +
               ", fecha='" + fecha + '\'' +
               ", monto=" + monto +
               ", formaPago='" + formaPago + '\'' +
               ", fechaVencimiento='" + fechaVencimiento + '\'' +
               ", estado='" + estado + '\'' +
               ", cajaBancoId=" + cajaBancoId +
               '}';
    }
}
