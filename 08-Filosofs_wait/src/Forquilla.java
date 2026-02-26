public class Forquilla {
    private int num;
    public static final int LLIURE = -1;
    private int propietari = LLIURE;

    public Forquilla(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getPropietari() {
        return propietari;
    }

    public void setPropietari(int propietari) {
        this.propietari = propietari;
    }
}