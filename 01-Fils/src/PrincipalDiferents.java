public class PrincipalDiferents {
    public static void main(String[] args) {
        Fil filPepe = new Fil("Pepe");
        Fil filJuan = new Fil("Juan");

        filPepe.setPriority(1);
        filJuan.setPriority(10);

        filPepe.start();
        filJuan.start();

        System.out.println("Acaba thread main");
    }
}
