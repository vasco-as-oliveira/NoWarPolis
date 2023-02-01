package com.NoWarPolis.City_Classes;

import edu.princeton.cs.algs4.*;

import java.util.List;
import java.util.*;

public class DoubleWeightedGraph {

    public ST<Integer, Double> times;
    public ST<Integer, Double> distances;
    public List<Way> ways;
    public EdgeWeightedDigraph weightedTime;
    public EdgeWeightedDigraph weightedDistance;


    public DoubleWeightedGraph(int V, List<Way> ways) {
        weightedTime = new EdgeWeightedDigraph(V);
        weightedDistance = new EdgeWeightedDigraph(V);
        this.times= new ST<>();
        this.distances=new ST<>();
        for (Way aux : ways){
            weightedDistance.addEdge(new DirectedEdge(aux.getFrom().id, aux.getTo().id, aux.getDistance()));
            times.put(this.weightedTime.E(), aux.time());
            weightedTime.addEdge(new DirectedEdge(aux.getFrom().id, aux.getTo().id, aux.time()));
            times.put(this.weightedDistance.E(), aux.getDistance());
        }

        this.ways=ways;
    }


       public boolean isGrafoConexo(EdgeWeightedDigraph graph) {
            for (DirectedEdge edge : graph.edges()) GFG.Add_edge(edge.from(), edge.to());
            return GFG.Is_connected(graph.V());
    }


}
