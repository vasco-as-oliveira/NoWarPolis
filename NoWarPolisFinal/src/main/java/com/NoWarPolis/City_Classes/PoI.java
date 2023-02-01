package com.NoWarPolis.City_Classes;


import edu.princeton.cs.algs4.BST;
import edu.princeton.cs.algs4.ST;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class PoI implements Serializable {  //Etiquetas
  public Integer PoiId;
  public String name;
  public Long vertice;
  public Integer timespent;
  public BST<Integer, ArrayList<User>> usersVisited; // Key = timestamp de chegada  Value = user
  public Node location;   //Node a que pertence

  public PoI(Node location, Integer poiId) {
    this.PoiId=poiId;
    this.location=location;
    this.usersVisited = new BST<>();
  }

  public ArrayList<User> usersThatVisitedBetweenTimes(int start, int end){ //Users que visitaram aquele PoI entre 2 tempos
  ArrayList<User> list = new ArrayList<>();
  for (int time : this.usersVisited.keys()){
    if ((time>=start && time <=end) || (time+this.timespent>=start && time+this.timespent<=end) || (time<=start && time+this.timespent>=end)){
      list.addAll(this.usersVisited.get(time));
    }
  }
  return list;
  }


  @Override
  public String toString() {
    return "PoI{" +
            "eue='" + name + '\'' +
            ", id=" + vertice +
            ", location=" + location +
            '}';
  }



}