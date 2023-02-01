package City;


public class Semaforo extends PoI {
    private static final int RED = 0;
    private static final int GREEN = 1;

    public int color;

    public int carsStopped(){
        if (this.color==GREEN) return 0;
        return 2; //Colocar um valor simulando um sensor
    }

    public void switchColor(){
        if (this.color==RED) this.color=GREEN;
        else this.color=RED;
    }
}