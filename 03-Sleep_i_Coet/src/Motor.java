import java.util.Random;

public class Motor extends Thread {
    volatile int potenciaO = 0;
    int potenciaA = 0;

    public void setPotencia(int p) {
        potenciaO = p;
    }

    @Override
    public void run() {
        Random random = new Random();
        char num = getName().charAt(getName().length() - 1);
        boolean arrancado = false; 

        while (true) {
            if (potenciaO > 0) {
                arrancado = true;
            }

            if (arrancado && potenciaO == 0 && potenciaA == 0) {
                break;
            }

            if (potenciaA != potenciaO) {
                int segundos = random.nextInt(2) + 1;
                try {
                    Thread.sleep(segundos * 1000);
                } catch (InterruptedException e) {
                    break;
                }

                if (potenciaA < potenciaO) {
                    potenciaA++;
                    System.out.printf("Motor %c: Incre. Objectiu: %d Actual: %d%n", num, potenciaO, potenciaA);
                } else if (potenciaA > potenciaO) {
                    potenciaA--;
                    System.out.printf("Motor %c: Decre. Objectiu: %d Actual: %d%n", num, potenciaO, potenciaA);
                }
            } else {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    break;
                }
            }
        }
    }
}