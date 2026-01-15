public class Administracio {
    static int numPoblacioActiva = 50;    
    static Treballador[] poblacioActiva = new Treballador[50];
    public static void main(String[] args) {
        

        for (int i = 1; i < numPoblacioActiva; i++) {
            poblacioActiva[i] = new Treballador(25000, 20, 65, "Ciutadà-"+i);
            poblacioActiva[i].start();
            try {
                poblacioActiva[i].join();
            } catch (Exception e) {
            }
            System.out.printf("Ciutadà-%d -> edat: %d / total: %.2f %n",i, poblacioActiva[i].getEdat(),poblacioActiva[i].getCobrat());
        }    
    }
}
