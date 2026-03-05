
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Forquilla {
    private int num;
    Lock bloqueig = new ReentrantLock(true);

    public Forquilla(int num) {
        this.num = num;
    }
    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
    public void agafar(){
        bloqueig.lock();
    }
    public void deixar(){
        if (((ReentrantLock) bloqueig).isHeldByCurrentThread()) {
            bloqueig.unlock();
            
        }
    }
    
}