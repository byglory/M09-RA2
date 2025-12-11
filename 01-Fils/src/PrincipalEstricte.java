public class PrincipalEstricte {
    void main(){
        Fil filPepe = new Fil("Pepe");
        Fil filJuan = new Fil("Juan");

        filPepe.setPriority(10);
        filJuan.setPriority(10);
        
        filPepe.start();
        filJuan.start();

        System.out.println("Acaba thread main");
    }
}
