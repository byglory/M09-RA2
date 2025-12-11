public class Fil extends Thread{
    private String nom;
    public Fil(String nom){
        this.nom = nom;
    }
    @Override
    public void run() {
        try {
            
        for (int i = 0; i <= 9; i++) {
            System.out.println(this.nom + " " + i);
            sleep(1);
        }
        System.out.println("Acaba el fil " + this.nom);
        } catch (Exception e) {
        }
        
    }
}
