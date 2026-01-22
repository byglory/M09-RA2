
import java.util.Random;

public class Soci extends Thread{
    static Compte compte = new Compte();
    public static Compte getCompte() {
        return compte;
    }

    static float aportacio = 10f;
    int esperaMax=100;
    Random random = new Random();
    static int maxAnys=10;

    @Override
    public void run(){
        for (int i = 0; i < maxAnys; i++) {
            for (int j = 0; j < 12; j++) {
                if(j%2==0){
                    compte.setSaldo(compte.getSaldo()+aportacio);
                }else{
                    compte.setSaldo(compte.getSaldo()-aportacio);
                }
                try {
                    Thread.sleep(random.nextInt(esperaMax));
                } catch (Exception e) {
                }

            }
        }
    }
}
