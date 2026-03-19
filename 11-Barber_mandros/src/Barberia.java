
import java.util.LinkedList;
import java.util.Random;

public class Barberia extends Thread{
    Random random = new Random();
    LinkedList<Client> salaEspera;
    int cadires;
    Object condBarber;

    static Barberia airebrab = new Barberia(3);

    public Barberia(int cadires) {
        this.cadires = cadires;
    }
    public Client seguentClient(){
        if(salaEspera != null) return salaEspera.getFirst();
        return null;
    }
    public void entraClient(Client enrique){
        if (cadires >= salaEspera.size()){ 
            salaEspera.add(enrique);
            System.out.println("Client " + enrique.getNom() +"en espera");
        }
        else System.out.println("No queden cadires, client " + enrique.getNom() + "se'n va");
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                wait(50);
                entraClient(new Client(i));
            }
            wait(100);
            for (int i = 10; i < 20; i++) {
                wait(50);
                entraClient(new Client(i));
            }
        } catch (InterruptedException e) {
                e.printStackTrace();
            }
        super.run();
    }
    
    public static void main(String[] args) {
    Barberia josecarlos = new Barberia(3);
    Barber jose = new Barber("Jose");
    jose.run();
    josecarlos.run();
    }

}
