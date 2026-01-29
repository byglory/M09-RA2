
import java.util.Random;

public class Soci extends Thread{
    static Compte compte = Compte.getInstance();
    public static Compte getCompte() {
        return compte;
    }
 
    static float aportacio = 10f;
    int esperaMax=100;
    Random random = new Random();
    static int maxAnys=10;

    @Override
    public void run(){
        for (int i = 1; i <= maxAnys; i++) {
            for (int j = 1; j <= 12; j++) {
                synchronized (compte) {
                    if(j%2==0){
                    compte.setSaldo(compte.getSaldo()+aportacio);
                }else{
                    compte.setSaldo(compte.getSaldo()-aportacio);
                }
                
                }
                try {
                    Thread.sleep(random.nextInt(esperaMax));
                } catch (Exception e) {
                }
                System.out.println("Soci: " +" Any: "+ i +" Mes: " + j + " Aportacio: " 
                                            + aportacio + "€ Saldo: " + compte.getSaldo()+"€");
            }
        }
    }
}
