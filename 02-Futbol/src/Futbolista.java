public class Futbolista extends Thread {
    private String nom;
    private int nGols;
    private int nTirades;
    
    private static final int NUM_JUGADORS = 11;
    private final int NUM_TIRADES = 20;
    private static final double PROBABILITAT = 0.5f;

    public Futbolista(String nom) {
        this.nom = nom;
        this.nGols = 0;
    }
    
    @Override
    public void run() {
        for (nTirades = 0; nTirades < NUM_TIRADES; nTirades++) {
            if (Math.random() > PROBABILITAT) {
                nGols++;
            }
        }
    }
    public static void main(String[] args) {
        System.out.println("Inici dels xuts--------------------");
        String[] nombres = {
            "Piqué", "Vinicius", "Torres", "Ramos", "Ronaldo", 
            "Lewan", "Belli", "Arnau", "Aspas", "Messi", "Mbapé"
        };
        Futbolista[] equipo = new Futbolista[NUM_JUGADORS];
        for (int i = 0; i < NUM_JUGADORS; i++) {
            equipo[i] = new Futbolista(nombres[i]);
        }
        for (int i = 0; i < NUM_JUGADORS; i++) {
            equipo[i].start();
        }
        for (int i = 0; i < NUM_JUGADORS; i++) {
            try {
                equipo[i].join();
            } catch (InterruptedException e) {
            }
        }
        System.out.println("Fi dels xuts-----------------------");
        System.out.println("--- Estadistiques ------");
        for (int i = 0; i < NUM_JUGADORS; i++) {
            System.out.printf("%s\t-> %d gols%n", 
                equipo[i].nom, 
                equipo[i].nGols
            );
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Futbolista{");
        sb.append("nom=").append(nom);
        sb.append(", nGols=").append(nGols);
        sb.append(", nTirades=").append(nTirades);
        sb.append('}');
        return sb.toString();
    }
}