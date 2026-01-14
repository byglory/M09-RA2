import java.util.Random;

public class DormAleatori extends Thread{
    
    private long millis;
    private static final int ITINERACIONES = 10;
    public DormAleatori(String nombre) {
        super(nombre);
        this.millis = System.currentTimeMillis();
    }
    

    @Override
    public void run(){
        Random random = new Random();
        int aleatori;
        long total;
        for (int i = 0; i<ITINERACIONES;i++){
            aleatori = random.nextInt(1000);
            total= System.currentTimeMillis() - this.millis;
            System.out.printf("%s(%d) a dormir\t%dms\ttotal\t%dms%n", getName(), i,aleatori,total);
            try {
                Thread.sleep(aleatori);
            } catch (Exception e) {
            }
        }
        
    }
    public static void main(String[] args) {
        DormAleatori Joan = new DormAleatori("Joan");
        DormAleatori Pep = new DormAleatori("Pep    ");
        Joan.start();
        Pep.start();
        System.out.println("Fi de main");

    }
    
}
