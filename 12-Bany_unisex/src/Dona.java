public class Dona extends Thread {
    String nom;

    @Override
    public void run() {
        entraHome();
        utilitzaLavabo(2,3);
        surtHome();
    }
}