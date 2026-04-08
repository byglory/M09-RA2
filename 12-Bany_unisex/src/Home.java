import java.util.concurrent.ThreadLocalRandom;

public class Home extends Thread {
    private final String nom;
    private final BanyUnisex lavabo;

    public Home(String nom, BanyUnisex lavabo) {
        this.nom = nom;
        this.lavabo = lavabo;
    }

    @Override
    public void run() {
        System.out.println(nom + " vol entrar al bany");
        lavabo.entraHome(nom);
        utilitzaLavabo(1000, 2000);
        lavabo.surtHome(nom);
        System.out.println(nom + " ha acabat d'usar el bany");
    }

    private void utilitzaLavabo(int minMillis, int maxMillis) {
        int temps = ThreadLocalRandom.current().nextInt(minMillis, maxMillis + 1);
        try {
            Thread.sleep(temps);
        } catch (InterruptedException ignored) {
        }
    }
}
