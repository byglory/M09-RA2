import java.util.Random;

public class Filosof extends Thread {
    private int idFilosof;
    private Forquilla forquillaEsquerra;
    private Forquilla forquillaDreta;
    private int gana;
    private Random random;

    public Filosof(int idFilosof, String nom, Forquilla forquillaEsquerra, Forquilla forquillaDreta) {
        super(nom);
        this.idFilosof = idFilosof;
        this.forquillaEsquerra = forquillaEsquerra;
        this.forquillaDreta = forquillaDreta;
        this.gana = 0;
        this.random = new Random();
    }

    private void esperarAmbWait(int min, int max) {
        int temps = random.nextInt(max - min + 1) + min;
        synchronized (this) {
            try {
                this.wait(temps);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void pensar() {
        System.out.println("Filòsof: " + getName() + " pensant");
        esperarAmbWait(1000, 2000);
    }

    public void menjar() {
        agafarForquilles();
        System.out.println("Filòsof: " + getName() + " menja");
        esperarAmbWait(1000, 2000);
        System.out.println("Filòsof: " + getName() + " ha acabat de menjar");
        gana = 0;
        dixarForquilles();
    }

    public void agafarForquilles() {
        boolean teDues = false;
        while (!teDues) {
            if (agafarForquillaEsquerra()) {
                if (agafarForquillaDreta()) {
                    teDues = true;
                } else {
                    dixarForquillaEsquerra();
                    gana++;
                    System.out.println("Filòsof: " + getName() + " deixa l'esquerra (" + forquillaEsquerra.getNum() + ") i espera. Gana=" + gana);
                    esperarAmbWait(500, 1000);
                }
            } else {
                gana++;
                System.out.println("Filòsof: " + getName() + " no pot agafar l'esquerra. Espera. Gana=" + gana);
                esperarAmbWait(500, 1000);
            }
        }
    }

    public boolean agafarForquillaEsquerra() {
        synchronized (forquillaEsquerra) {
            if (forquillaEsquerra.getPropietari() == Forquilla.LLIURE) {
                forquillaEsquerra.setPropietari(idFilosof);
                System.out.println("Filòsof: " + getName() + " agafa la forquilla esquerra " + forquillaEsquerra.getNum());
                return true;
            }
            return false;
        }
    }

    public boolean agafarForquillaDreta() {
        synchronized (forquillaDreta) {
            if (forquillaDreta.getPropietari() == Forquilla.LLIURE) {
                forquillaDreta.setPropietari(idFilosof);
                System.out.println("Filòsof: " + getName() + " agafa la forquilla dreta " + forquillaDreta.getNum());
                return true;
            }
            return false;
        }
    }

    public void dixarForquillaEsquerra() {
        synchronized (forquillaEsquerra) {
            forquillaEsquerra.setPropietari(Forquilla.LLIURE);
            forquillaEsquerra.notifyAll();
        }
    }

    public void dixarForquilles() {
        synchronized (forquillaDreta) {
            forquillaDreta.setPropietari(Forquilla.LLIURE);
            forquillaDreta.notifyAll();
        }
        dixarForquillaEsquerra();
    }

    @Override
    public void run() {
        while (true) {
            pensar();
            menjar();
        }
    }
}