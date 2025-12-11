public class PrincipalIguals {
    void main(){
        Fil filPepe = new Fil("Pepe");
        Fil filJuan = new Fil("Juan");

        filJuan.setPriority(10);
        filPepe.setPriority(10);

        filPepe.start();
        filJuan.start();

        System.out.println("Acaba thread main");
    }
}
