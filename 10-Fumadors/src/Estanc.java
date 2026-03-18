import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Estanc extends Thread {
    private List<Tabac> llistaTabac;
    private List<Paper> llistaPaper;
    private List<Llumi> llistaLlumins;
    private boolean obert;
    private Random random;

    public Estanc() {
        this.llistaTabac = new ArrayList<>();
        this.llistaPaper = new ArrayList<>();
        this.llistaLlumins = new ArrayList<>();
        this.obert = true;
        this.random = new Random();
    }

    public synchronized void addTabac() {
        llistaTabac.add(new Tabac());
        System.out.println("Afegint tabac");
        notifyAll(); // Avisem als fumadors que esperen
    }

    public synchronized void addLlumi() {
        llistaLlumins.add(new Llumi());
        System.out.println("Afegint Llumí");
        notifyAll();
    }

    public synchronized void addPaper() {
        llistaPaper.add(new Paper());
        System.out.println("Afegint Paper");
        notifyAll();
    }

    public void nouSubministrament() {
        int recurs = random.nextInt(3);
        if (recurs == 0) {
            addTabac();
        } else if (recurs == 1) {
            addPaper();
        } else {
            addLlumi();
        }
    }

    public synchronized Tabac venTabac() throws InterruptedException {
        while (llistaTabac.isEmpty() && obert) {
            wait(); // Espera fins que hi hagi tabac
        }
        if (!llistaTabac.isEmpty()) {
            return llistaTabac.remove(llistaTabac.size() - 1);
        }
        return null;
    }

    public synchronized Paper venPaper() throws InterruptedException {
        while (llistaPaper.isEmpty() && obert) {
            wait(); // Espera fins que hi hagi paper
        }
        if (!llistaPaper.isEmpty()) {
            return llistaPaper.remove(llistaPaper.size() - 1);
        }
        return null;
    }

    public synchronized Llumi venLlumi() throws InterruptedException {
        while (llistaLlumins.isEmpty() && obert) {
            wait(); // Espera fins que hi hagi llumins
        }
        if (!llistaLlumins.isEmpty()) {
            return llistaLlumins.remove(llistaLlumins.size() - 1);
        }
        return null;
    }

    public synchronized void tancarEstanc() {
        this.obert = false;
        notifyAll(); // Despertem qualsevol fil que hagi quedat penjat per seguretat
    }

    @Override
    public void run() {
        System.out.println("Estanc obert");
        while (obert) {
            nouSubministrament();
            try {
                // Temps d'espera entre 0,5 i 1,5 segons (500 a 1500 ms)
                Thread.sleep(500 + random.nextInt(1001));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}