import java.util.Random;

public class Treballador extends Thread{
    float souAnualBrut;
    int edatIniciTreball;
    int edatFiTreball;
    String nom;
    int edatActual;
    float cobrat;
    Random rnd;

    public Treballador(float souAnualBrut, int edatIniciTreball, int edatFiTreball, String nom) {
        this.souAnualBrut = souAnualBrut;
        this.edatIniciTreball = edatIniciTreball;
        this.edatFiTreball = edatFiTreball;
        this.nom = nom;
        this.edatActual = 0;
        this.cobrat = 0.0f;
    }
   public void cobra() {
    cobrat += souAnualBrut / 12.0f;
}

public void pagaImpostos() {
    cobrat -= (souAnualBrut / 12.0f) * 0.24f;
}


    public float getCobrat(){
        return cobrat;
    }
    public int getEdat(){
        return edatActual;
    }
    @Override
    public void run(){
        for (int i = edatActual; i < edatFiTreball; i++) {
            if (edatActual >= edatIniciTreball){
                for (int j = 1; j <= 12 ; j++){
                    cobra();
                pagaImpostos();
                }
            }
            edatActual++;

        }
    }
}
