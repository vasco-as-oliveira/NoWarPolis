package com.NoWarPolis.City_Classes;

import edu.princeton.cs.algs4.ST;

import java.io.Serializable;
import java.util.ArrayList;

public class Node implements Serializable {
    public Node(){

    }

    public Node(Integer id, Long idPos,Double x, Double y, String type) {
        this.id = id;
        this.idPos=idPos;
        this.lat=x;
        this.lon=y;
        pointsOfInteress = new ArrayList<>();
        this.interruptions = new ArrayList<>();
        this.type = type;

    }
    public Long idPos;
    public Integer id;
    public Double lat, lon;
    public ArrayList<PoI> pointsOfInteress;
    public ArrayList<ArrayList<Double>> interruptions;    //Key - init    Value - end
    public String type;

    /**
     * interrputs a node between times
     * @param init initTime
     * @param end endTime
     */

    public void AddInterruption(double init, double end){
        ArrayList<Double> aux = new ArrayList<>();
        aux.add(init);
        aux.add(end);
        this.interruptions.add(aux);
    }

    public float distance(Node to){
        return (float) Math.sqrt((to.lon - this.lon) * (to.lon - this.lon) + (to.lat - this.lat) * (to.lat - this.lat));
    }

    /**
     * checks if node is overlotated in a certain time
     */

    public boolean isOverLotated(double timestamp){
        int userCount=0;
        for (PoI poi : this.pointsOfInteress){
            userCount+=poi.usersThatVisitedBetweenTimes((int) timestamp, (int) timestamp).size();
        }
        return userCount > 20;
    }

    /**
     * checks if a node is interrupted in a certain time
     */

    public boolean isInterrupted(double timestamp){
        for (ArrayList<Double> aux : interruptions){

            if (timestamp>=aux.get(0) && timestamp<=aux.get(1)) return true;

        }

        return false;
    }

    @Override
    public String toString() {
        return "Node{" + this.idPos + "}";

    }
}