package com.NoWarPolis.City_Classes.Points_Of_Interest;


import com.NoWarPolis.City_Classes.Node;
import com.NoWarPolis.City_Classes.PoI;
import com.NoWarPolis.City_Classes.Veiculo;
import edu.princeton.cs.algs4.RedBlackBST;

import java.util.ArrayList;

public class Semaforo extends PoI {
    private static final int RED = 0;
    private static final int GREEN = 1;

    public Integer color;
    public RedBlackBST<Integer, ArrayList<Veiculo>> carsStoppedAtTime;


    public Semaforo(Node node, Integer id) {
        super(node, id);
        this.name="semaforo";
        carsStoppedAtTime = new RedBlackBST<>();
        this.timespent=50;
    }

    /**
     * Checks if color is green in a certain time
     * @param timestamp time
     */

    public boolean isGreen(int timestamp){
        if(((timestamp/100)%10)%2 == 0){
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * @param timestamp time
     * @param veiculo veiculo
     * @return time till green
     */

    public int carsStoppedBetweenTime(int timestamp, Veiculo veiculo){
        if(!isGreen(timestamp)) {
            if (!carsStoppedAtTime.contains(timestamp)) {
                carsStoppedAtTime.put(timestamp, new ArrayList<>());
            }
            else carsStoppedAtTime.get(timestamp).add(veiculo);
        }
        return ((timestamp/100%10%2)*100)+100;
    }

    @Override
    public String toString() {
        return "semaforo" + "," + this.location.id + "," + this.location.idPos;
    }
}