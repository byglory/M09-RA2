import java.util.Random;

public class Filosof extends Thread {

    private int idFilosof;
    private Forquilla forquillaEsquerra;
    private Forquilla forquillaDreta;

    private long iniciGana;
    private long fiGana;
    private long gana;

    private Random random = new Random();

    public Filosof(int idFilosof, String nom, Forquilla esquerra, Forquilla dreta) {
        super(nom);
        this.idFilosof = idFilosof;
        this.forquillaEsquerra = esquerra;
        this.forquillaDreta = dreta;
    }

    public long calcularGana() {
        gana = (fiGana - iniciGana) / 1000;
        return gana;
    }

    public void resetGana() {
        iniciGana = 0;
        fiGana = 0;
        gana = 0;
    }

    public void pensar() {
        try {
            iniciGana = System.currentTimeMillis();
            System.out.println(getName() + " pensant");
            Thread.sleep(1000 + random.nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void menjar() {

        agafarForquilles();

        fiGana = System.currentTimeMillis();
        calcularGana();

        System.out.println(getName() + " té forquilles esq(" 
                + forquillaEsquerra.getNum() + ") dreta (" 
                + forquillaDreta.getNum() + ")");

        System.out.println(getName() + " menja amb gana " + gana);

        try {
            Thread.sleep(1000 + random.nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(getName() + " ha acabat de menjar");

        dixarForquilles();

        System.out.println(getName() + " deixa les forquilles");

        resetGana();
    }

    public void agafarForquilles() {
        agafarForquillaEsquerra();
        agafarForquillaDreta();
    }

    public void agafarForquillaEsquerra() {
        forquillaEsquerra.agafar();
    }

    public void agafarForquillaDreta() {
        forquillaDreta.agafar();
    }

    public void dixarForquilles() {
        forquillaDreta.deixar();
        forquillaEsquerra.deixar();
    }

    @Override
    public void run() {
        while (true) {
            pensar();
            menjar();
        }
    }
}