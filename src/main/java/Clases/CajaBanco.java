
package Clases;
public class CajaBanco {
    private int cajaBancoId;
    private String nombre;
    private double saldo;

    public CajaBanco(int cajaBancoId, String nombre, double saldo) {
        this.cajaBancoId = cajaBancoId;
        this.nombre = nombre;
        this.saldo = saldo;
    }

    public int getCajaBancoId() {
        return cajaBancoId;
    }

    public void setCajaBancoId(int cajaBancoId) {
        this.cajaBancoId = cajaBancoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}

