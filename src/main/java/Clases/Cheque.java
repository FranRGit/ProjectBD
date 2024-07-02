
package Clases;
public class Cheque extends MedioPago {
    private String numeroCheque;
    private String banco;

    public Cheque(int pagoId, int facturaId, double monto, String fecha, String numeroCheque, String banco) {
        super(pagoId, facturaId, monto, fecha);
        this.numeroCheque = numeroCheque;
        this.banco = banco;
    }

    public String getNumeroCheque() {
        return numeroCheque;
    }

    public void setNumeroCheque(String numeroCheque) {
        this.numeroCheque = numeroCheque;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    @Override
    public String getTipo() {
        return "Cheque";
    }

    @Override
    public String toString() {
        return super.toString() + ", Tipo: Cheque, Numero Cheque: " + numeroCheque + ", Banco: " + banco;
    }
}

