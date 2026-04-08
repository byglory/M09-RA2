import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class BanyUnisex {
    public static final int BANY_BUIT = 0;
    public static final int BANY_AMB_HOMES = 1;
    public static final int BANY_AMB_DONES = 2;

    private int estatActual = BANY_BUIT;
    private int ocupants = 0;
    public static final int CAPACITAT_MAX = 3;

    private final Semaphore capacitat = new Semaphore(CAPACITAT_MAX, true);
    private final ReentrantLock lockEstat = new ReentrantLock(true);

    public void entraHome(String nom) {
        while (true) {
            lockEstat.lock();
            try {
                if (estatActual == BANY_BUIT || estatActual == BANY_AMB_HOMES) {
                    if (capacitat.tryAcquire()) {
                        ocupants++;
                        estatActual = BANY_AMB_HOMES;
                        System.out.println("Home entra al bany. Ocupants: " + ocupants);
                        return;
                    }
                }
            } finally {
                lockEstat.unlock();
            }
            sleepBrief();
        }
    }

    public void entraDona(String nom) {
        while (true) {
            lockEstat.lock();
            try {
                if (estatActual == BANY_BUIT || estatActual == BANY_AMB_DONES) {
                    if (capacitat.tryAcquire()) {
                        ocupants++;
                        estatActual = BANY_AMB_DONES;
                        System.out.println("Dona entra en el bany. Ocupants: " + ocupants);
                        return;
                    }
                }
            } finally {
                lockEstat.unlock();
            }
            sleepBrief();
        }
    }

    public void surtHome(String nom) {
        lockEstat.lock();
        try {
            ocupants--;
            capacitat.release();
            System.out.println("Home surt del bany. Ocupants: " + ocupants);
            if (ocupants == 0) {
                estatActual = BANY_BUIT;
                System.out.println("El bany està buit");
            }
        } finally {
            lockEstat.unlock();
        }
    }

    public void surtDona(String nom) {
        lockEstat.lock();
        try {
            ocupants--;
            capacitat.release();
            System.out.println("Dona surt del bany. Ocupants: " + ocupants);
            if (ocupants == 0) {
                estatActual = BANY_BUIT;
                System.out.println("El bany està buit");
            }
        } finally {
            lockEstat.unlock();
        }
    }

    private void sleepBrief() {
        try {
            Thread.sleep(20);
        } catch (InterruptedException ignored) {
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BanyUnisex lavabo = new BanyUnisex();
        Home[] homes = new Home[5];
        Dona[] dones = new Dona[5];

        for (int i = 0; i < homes.length; i++) {
            homes[i] = new Home("Home-" + i, lavabo);
        }
        for (int i = 0; i < dones.length; i++) {
            dones[i] = new Dona("Dona-" + i, lavabo);
        }

        for (Home home : homes) {
            home.start();
            Thread.sleep(50);  // Pausa pequeña para permitir ejecución en orden
        }
        for (Dona dona : dones) {
            dona.start();
            Thread.sleep(50);
        }

        for (Home home : homes) {
            home.join();
        }
        for (Dona dona : dones) {
            dona.join();
        }
    }
}
