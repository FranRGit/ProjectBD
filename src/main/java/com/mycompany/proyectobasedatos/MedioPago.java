
package com.mycompany.proyectobasedatos;

public abstract class MedioPago {
    private int facturaId;
    private double monto;
    private String fecha;

    public MedioPago(int pagoId, int facturaId, double monto, String fecha) {
        this.facturaId = facturaId;
        this.monto = monto;
        this.fecha = fecha;
    }

    public int getFacturaId() {
        return facturaId;
    }

    public void setFacturaId(int facturaId) {
        this.facturaId = facturaId;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public abstract String getTipo();
}
