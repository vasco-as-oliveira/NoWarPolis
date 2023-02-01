package com.NoWarPolis.City_Classes;




public class Semaforo extends PoI {
    private static final int RED = 0;
    private static final int GREEN = 1;

    public Integer color;

    public Semaforo(Integer color, Node node, Integer id) {
        super(node, id);
        this.color=color;
        this.timespent = 90;
        this.name="semaforo";
    }

    public int carsStopped(){
        if (this.color==GREEN) return 0;
        return 2; //Colocar um valor simulando um sensor
    }

    public void switchColor(){
        if (this.color==RED) this.color=GREEN;
        else this.color=RED;
    }

    @Override
    public String toString() {
        return super.toString() + ", " + this.color;
    }
}