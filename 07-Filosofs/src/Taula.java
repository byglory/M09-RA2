public class Taula {
    private Forquilla[] forquilles; 
    private Filosof[] comensals;
    
    public Taula(int numComensals) {
        forquilles = new Forquilla[numComensals]; 
        comensals = new Filosof[numComensals];
        for(int i = 0; i < numComensals; i++) {
            forquilles[i] = new Forquilla(i);
       }
        for (int i = 0; i < numComensals; i++) {
            Forquilla esquerra = forquilles[1];
            Forquilla dreta = forquilles[(i+1)%numComensals];
            comensals[i] = new Filosof("fil " + i,esquerra,dreta); 
        }
    }
    public void showTaula(){
        
        for (int i = 0; i < comensals.length; i++) {
            int esquerra = forquilles[i].getNum();
            int dreta = forquilles[(i+1)%comensals.length].getNum();
            System.out.println("Comensal:fil" + i + " esq:" + esquerra + " dret:" + dreta);  
        }
    }
    public void cridarATaula(){
        for (int i = 0; i < comensals.length; i++){ 
            comensals[i].start();
        }

}
public static void main(String[] args) {
    Taula taula = new Taula(4);
    taula.showTaula();
    taula.cridarATaula();
}
}

