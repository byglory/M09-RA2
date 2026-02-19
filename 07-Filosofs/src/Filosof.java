import java.util.Random;
public class Filosof extends Thread {
    private Forquilla forquillaEsquerra;
    private Forquilla forquillaDreta;
    private int gana;
    private Random random;

    public Filosof(String nom, Forquilla forquillaEsquerra, Forquilla forquillaDreta) {
        super(nom);
        this.forquillaEsquerra = forquillaEsquerra;
        this.forquillaDreta = forquillaDreta;
        this.gana = 0;
        this.random = new Random();
    }

    public void pensar() {
        System.out.println("Filòsof: " + getName() + " pensant");
        try {
            Thread.sleep(random.nextInt(1001) + 1000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void menjar() {
        boolean haMenjat = false;
        
        while (!haMenjat) {
            boolean teEsquerra = false;
            boolean teDreta = false;
            
            synchronized (forquillaEsquerra) {
                if (!forquillaEsquerra.isEnUs()) {
                    forquillaEsquerra.setEnUs(true);
                    teEsquerra = true;
                    System.out.println("Filòsof: " + getName() + " agafa la forquilla esquerra " + forquillaEsquerra.getNumero());
                }
            }

            if (teEsquerra) {
                synchronized (forquillaDreta) {
                    if (!forquillaDreta.isEnUs()) {
                        forquillaDreta.setEnUs(true);
                        teDreta = true;
                        System.out.println("Filòsof: " + getName() + " agafa la forquilla dreta " + forquillaDreta.getNumero());
                    }
                }

                if (teDreta) {
                    System.out.println("Filòsof: " + getName() + " menja");
                    try {
                        Thread.sleep(random.nextInt(1001) + 1000); 
                    } catch (InterruptedException e) {}
                    
                    System.out.println("Filòsof: " + getName() + " ha acabat de menjar");
                    gana = 0; 
                    
                    synchronized (forquillaDreta) { forquillaDreta.setEnUs(false); }
                    synchronized (forquillaEsquerra) { forquillaEsquerra.setEnUs(false); }
                    
                    haMenjat = true; 
                    
                } else {
                    synchronized (forquillaEsquerra) {
                        forquillaEsquerra.setEnUs(false);
                    }
                    System.out.println("Filòsof: " + getName() + " deixa l'esquerra(" + forquillaEsquerra.getNumero() + ") i espera (dreta ocupada)");
                    gana++;
                    System.out.println("Filòsof: " + getName() + " gana=" + gana);
                    esperarPerTornarAIntentar();
                }
            } else {
                gana++;
                esperarPerTornarAIntentar();
            }
        }
    }

    private void esperarPerTornarAIntentar() {
        try {
            Thread.sleep(random.nextInt(501) + 500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            pensar();
            menjar();
        }
    }
}
