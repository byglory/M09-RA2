public class PrincipalEstricte {
    void main(){
        Fil filPepe = new Fil("Pepe");
        Fil filJuan = new Fil("Juan");

        filPepe.setPriority(1);
        filJuan.setPriority(1);
        
        filJuan.start();
        try{
            Thread.sleep(1);
        }catch (InterruptedException e){

        }
        
        filPepe.start();
        

        System.out.println("Acaba thread main");
    }
}
