
package com.mycompany.proyectobasedatos;
public class Canje extends MedioPago {
    private String descripcion;

    public Canje(int pagoId, int facturaId, double monto, String fecha, String descripcion) {
        super(pagoId, facturaId, monto, fecha);
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String getTipo() {
        return "Canje";
    }

    @Override
    public String toString() {
        return super.toString() + ", Tipo: Canje, Descripcion: " + descripcion;
    }
}
