
package com.mycompany.proyectobasedatos;
public class Transferencia extends MedioPago {
    private String numeroCuenta;
    private String banco;

    public Transferencia(int pagoId, int facturaId, double monto, String fecha, String numeroCuenta, String banco) {
        super(pagoId, facturaId, monto, fecha);
        this.numeroCuenta = numeroCuenta;
        this.banco = banco;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    @Override
    public String getTipo() {
        return "Transferencia";
    }

    @Override
    public String toString() {
        return super.toString() + ", Tipo: Transferencia, Numero Cuenta: " + numeroCuenta + ", Banco: " + banco;
    }
}