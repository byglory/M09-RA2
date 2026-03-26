public class Home extends Thread {
    String nom;

    @Override
    public void run() {
        entraHome();
        utilitzaLavabo(1,2);
        surtHome();
    }
    
}
