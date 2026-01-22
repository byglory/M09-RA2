public class Associacio {
    static int numSocis = 1000;
    static Soci socis[] = new Soci[numSocis];

    public Associacio() {
    }
    
    public static void iniciaCompteTempsSocis(){
        for (int i = 0; i < numSocis; i++) {
            socis[i] = new Soci();
            socis[i].start();
        }
    }

    public static void esperaPeriodeSoci(){
        for (int i = 0; i < numSocis; i++) {
            try {
                socis[i].join();
            } catch (Exception e) {
            }
        }
    }

    public static void mostraBalancCompte(){
        System.out.println(Compte.getInstance().getSaldo());
    }
    public static void main(String[] args) {
        iniciaCompteTempsSocis();
        esperaPeriodeSoci();
        mostraBalancCompte();
    }
}
