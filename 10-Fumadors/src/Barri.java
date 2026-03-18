public class Barri {
    private Estanc estanc;
    private Fumador[] fumadors;

    public Barri() {
        // Inicialitzem l'estanc
        estanc = new Estanc();
        // Inicialitzem l'array de 3 fumadors
        fumadors = new Fumador[3];
        for (int i = 0; i < 3; i++) {
            fumadors[i] = new Fumador(estanc, i);
        }
    }

    public void iniciarDia() {
        // Posem en marxa els fumadors
        for (Fumador f : fumadors) {
            f.start();
        }
        // Posem en marxa l'estanc
        estanc.start();

        // Esperem que els fumadors acabin (3 cigarretes cadascú)
        for (Fumador f : fumadors) {
            try {
                f.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Un cop han acabat, tanquem l'estanc i acabem l'execució
        estanc.tancarEstanc();
    }

    public static void main(String[] args) {
        Barri barri = new Barri();
        barri.iniciarDia();
    }
}