import java.util.Scanner;

public class Coet {
    static Motor motor0 = new Motor();
    static Motor motor1 = new Motor();
    static Motor motor2 = new Motor();
    static Motor motor3 = new Motor();

    public static void arranca() {
        motor0.start();
        motor1.start();
        motor2.start();
        motor3.start();
    }

    public void passaAPotencia(int p) {
        if (p >= 0 && p <= 10) {
            System.out.println("Passant a potència " + p);
            motor0.setPotencia(p);
            motor1.setPotencia(p);
            motor2.setPotencia(p);
            motor3.setPotencia(p);
        } else {
            System.out.println("El numero no pot ser superior a 10 o inferior a 0");
        }
    }

    public static void main(String[] args) {
        Coet coet = new Coet();
        Scanner sc = new Scanner(System.in);

        arranca();

        int p;
        do {
            p = sc.nextInt();
            coet.passaAPotencia(p);
        } while (p != 0);

        sc.close();
        
    }
}
