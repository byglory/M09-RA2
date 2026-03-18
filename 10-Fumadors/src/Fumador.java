import java.util.Random;

class Fumador extends Thread {
    private Estanc estanc;
    private int id;
    private Tabac unTabac;
    private Llumi unLlumi;
    private Paper unPaper;
    private int numeroFumades;
    private Random rand;

    public Fumador(Estanc estanc, int id) {
        this.estanc = estanc;
        this.id = id;
        this.numeroFumades = 0;
        this.rand = new Random();
    }

    public void compraTabac() throws InterruptedException {
        System.out.println("Fumador " + id + " comprant Tabac");
        unTabac = estanc.venTabac();
    }

    public void compraPaper() throws InterruptedException {
        System.out.println("Fumador " + id + " comprant Paper");
        unPaper = estanc.venPaper();
    }

    public void compraLlumi() throws InterruptedException {
        System.out.println("Fumador " + id + " comprant Llumí");
        unLlumi = estanc.venLlumi();
    }

    public void fuma() throws InterruptedException {
        // Només fuma si té tots els elements
        if (unTabac != null && unPaper != null && unLlumi != null) {
            unTabac = null;
            unPaper = null;
            unLlumi = null;
            
            System.out.println("Fumador " + id + " fumant");
            // Fuma durant un període de 0.5s a 1s
            Thread.sleep(500 + rand.nextInt(501)); 
            
            numeroFumades++;
            System.out.println("Fumador " + id + " ha fumat " + numeroFumades + " vegades");
        }
    }

    @Override
    public void run() {
        try {
            while (numeroFumades < 3) {
                compraTabac();
                compraPaper();
                compraLlumi();
                fuma();
            }
        } catch (InterruptedException e) {
            System.out.println("Fumador " + id + " interromput.");
        }
    }
}