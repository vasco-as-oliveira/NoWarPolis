package com.NoWarPolis.City_Classes;

import edu.princeton.cs.algs4.BST;
import edu.princeton.cs.algs4.Graph;

import java.io.Serializable;
import java.util.ArrayList;

public class Visit implements Serializable {        //Esta classe é o histórico de uma visita
    private int id;
    private User client;
    private Integer initTime, endTime;
    private Node firstPosition, lastPosition;
    private Double distance;
    private PoI poiBeingVisited;
    private Veiculo method;
    private BST<Integer, Node> timeInNode;

    /**
     *
     * @param id ID Of travel
     * @param client The client
     * @param initTime The time when the visit starts
     * @param endTime The time when the visit ends
     * @param firstPosition the node where it started
     * @param lastPosition the node where he ended
     * @param distance the distance it made during the visit
     * @param poiBeingVisited the poi thats being visited
     * @param method the vehicle he goes
     * @param timeInNode the times and correspondent nodes through the visit
     */

    public Visit(int id, User client, Integer initTime, Integer endTime, Node firstPosition, Node lastPosition, Double distance, PoI poiBeingVisited, Veiculo method, BST<Integer, Node> timeInNode) {
        this.id = id;
        this.client = client;
        this.initTime = initTime;
        this.endTime = endTime;
        this.firstPosition = firstPosition;
        this.lastPosition = lastPosition;
        this.distance = distance;
        this.poiBeingVisited = poiBeingVisited;
        this.method = method;
        this.timeInNode = timeInNode;
    }

    public Visit(){
        timeInNode = new BST<>();
    }
    
    public Visit(User client, Integer initTime, Veiculo method) {
        timeInNode = new BST<>();
        this.client=client;
        this.initTime=initTime;
        this.method=method;
    }



    //Getters and Setters


    public PoI getPoiBeingVisited() {
        return poiBeingVisited;
    }

    public void setPoiBeingVisited(PoI poiBeingVisited) {
        this.poiBeingVisited = poiBeingVisited;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getClient() {
        return client;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }

    public Integer getInitTime() {
        return initTime;
    }

    public void setInitTime(Integer initTime) {
        this.initTime = initTime;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Node getFirstPosition() {
        return firstPosition;
    }

    public void setFirstPosition(Node firstPosition) {
        this.firstPosition = firstPosition;
    }

    public Node getLastPosition() {
        return lastPosition;
    }

    public void setLastPosition(Node lastPosition) {
        this.lastPosition = lastPosition;
    }

    public Veiculo getMethod() {
        return method;
    }

    public void setMethod(Veiculo method) {
        this.method = method;
    }

    public BST<Integer, Node> getTimeInNode() {
        return timeInNode;
    }

    public void setTimeInNode(BST<Integer, Node> timeInNode) {
        this.timeInNode = timeInNode;
    }





    @Override
    public String toString() {
        return "Visit{" +
                "id=" + id +
                ", client=" + client +
                ", initTime=" + initTime +
                ", endTime=" + endTime +
                ", firstPosition=" + firstPosition.idPos +
                ", lastPosition=" + lastPosition.idPos +
                ", distance=" + distance +
                //", method=" + method.getId() +
                '}';
    }
}
