public class Compte {
    float saldo;
    private static Compte instancia;
    public Compte(){}
    public static synchronized Compte getInstance() {
        if (instancia == null) {
            instancia = new Compte();
        }
        return instancia;
    } 
    public Compte(int saldo) {
        this.saldo = saldo;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }
    
    
}
