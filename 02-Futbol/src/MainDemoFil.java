public class MainDemoFil {
    void main(){
        Thread filActual = Thread.currentThread();
        System.out.println("MainDemoFil.main:");
        System.out.printf("Prioritat -> %d, Nom -> %s %n", 
                          filActual.getPriority(), 
                          filActual.getName());
        System.out.println("toString () -> " + filActual.toString());
    }
}