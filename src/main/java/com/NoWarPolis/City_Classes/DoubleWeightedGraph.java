package com.NoWarPolis.City_Classes;

import com.NoWarPolis.City_Classes.Vehicles.Carro;
import edu.princeton.cs.algs4.*;


import java.io.*;
import java.util.List;
import java.util.*;

public class DoubleWeightedGraph {

    public ST<Integer, Double> times;
    public ST<Integer, Double> distances;

    public ST<Integer, ArrayList<Boolean>> posgraphs;
    public ArrayList<Integer> restrictedNodes;
    public List<Way> ways;
    public EdgeWeightedDigraph weightedTime;
    public EdgeWeightedDigraph weightedDistance;
    public EdgeWeightedDigraph subgraphWeighetTime_Highway;
    public EdgeWeightedDigraph subgraphWeighetDist_Highway;
    public EdgeWeightedDigraph subgraphWeighetDist_Park;
    public EdgeWeightedDigraph subgraphWeighetTime_Park;



    public void addWay(Way aux, boolean isOneWay){
        weightedDistance.addEdge(new DirectedEdge(aux.getFrom().id, aux.getTo().id, aux.getDistance()));
        times.put(this.weightedTime.E(), aux.time(new Carro(0, null)));
        weightedTime.addEdge(new DirectedEdge(aux.getFrom().id, aux.getTo().id, aux.time(new Carro(0, null))));
        times.put(this.weightedDistance.E(), aux.getDistance());
        if(!isOneWay){
            weightedDistance.addEdge(new DirectedEdge(aux.getTo().id, aux.getFrom().id, aux.getDistance()));
            times.put(this.weightedTime.E(), aux.time(new Carro(0, null)));
            weightedTime.addEdge(new DirectedEdge(aux.getTo().id, aux.getFrom().id, aux.time(new Carro(0, null))));
            times.put(this.weightedDistance.E(), aux.getDistance());
        }
    }

    /**
     * Read from bin
     */

    public DoubleWeightedGraph(){
        try {
            File file = new File("src/main/java/com/NoWarPolis/Files/dataset1_graphAndSTs.bin");
            FileInputStream fos = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fos);

            readBinList(ois);
            readBinGraph(ois);
            readBinOneSt(ois);
            readBinOneStList(ois);

            ois.close();



        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     *
     *  Read from bin
     *
     */

    public void readBinList(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        this.restrictedNodes = new ArrayList<>();
        int size = (Integer) ois.readObject();
        for(int i=0; i<size; i++){
            this.restrictedNodes.add((Integer) ois.readObject());
        }

    }

    /**
     *
     *  Read from bin
     *
     */

    public void readBinGraph(ObjectInputStream ois) throws IOException, ClassNotFoundException {

        /*int i = (ArrayList<Node>) ois.readObject();
        System.out.println(i);*/
        this.weightedTime = new EdgeWeightedDigraph((Integer) ois.readObject());
        int numOfEdges = (Integer) ois.readObject();
        for(int i=0; i<numOfEdges; i++){
            DirectedEdge directedEdge = new DirectedEdge((Integer) ois.readObject(), (Integer) ois.readObject(), (Double) ois.readObject());
            weightedTime.addEdge(directedEdge);
        }

        this.weightedDistance = new EdgeWeightedDigraph((Integer) ois.readObject());
        int numOfEdges1 = (Integer) ois.readObject();
        for(int i=0; i<numOfEdges1; i++){
            DirectedEdge directedEdge = new DirectedEdge((Integer) ois.readObject(), (Integer) ois.readObject(), (Double) ois.readObject());
            weightedDistance.addEdge(directedEdge);
        }

        this.subgraphWeighetTime_Highway = new EdgeWeightedDigraph((Integer) ois.readObject());
        int numOfEdges2 = (Integer) ois.readObject();
        for(int i=0; i<numOfEdges2; i++){
            DirectedEdge directedEdge = new DirectedEdge((Integer) ois.readObject(), (Integer) ois.readObject(), (Double) ois.readObject());
            subgraphWeighetTime_Highway.addEdge(directedEdge);
        }

        this.subgraphWeighetDist_Highway = new EdgeWeightedDigraph((Integer) ois.readObject());
        int numOfEdges3 = (Integer) ois.readObject();
        for(int i=0; i<numOfEdges3; i++){
            DirectedEdge directedEdge = new DirectedEdge((Integer) ois.readObject(), (Integer) ois.readObject(), (Double) ois.readObject());
            subgraphWeighetDist_Highway.addEdge(directedEdge);
        }

        this.subgraphWeighetDist_Park = new EdgeWeightedDigraph((Integer) ois.readObject());
        int numOfEdges4 = (Integer) ois.readObject();
        for(int i=0; i<numOfEdges4; i++){
            DirectedEdge directedEdge = new DirectedEdge((Integer) ois.readObject(), (Integer) ois.readObject(), (Double) ois.readObject());
            subgraphWeighetDist_Park.addEdge(directedEdge);
        }

        this.subgraphWeighetTime_Park = new EdgeWeightedDigraph((Integer) ois.readObject());
        int numOfEdges5 = (Integer) ois.readObject();
        for(int i=0; i<numOfEdges5; i++){
            DirectedEdge directedEdge = new DirectedEdge((Integer) ois.readObject(), (Integer) ois.readObject(), (Double) ois.readObject());
            subgraphWeighetTime_Park.addEdge(directedEdge);
        }


    }

    /**
     *
     *  Read from bin
     *
     */

    private void readBinOneStList(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        this.posgraphs = new ST<>();
        int size = (Integer) ois.readObject();
        for(int i=0; i<size; i++){
            int key = (Integer) ois.readObject();
            this.posgraphs.put(key, new ArrayList<Boolean>()); //Tornar
            int sizeAux = (Integer) ois.readObject();
            for(int j=0; j<sizeAux; j++){
                this.posgraphs.get(key).add((Boolean) ois.readObject());
            }
        }
    }

    /**
     *
     *  Read from bin
     *
     */

    private void readBinOneSt(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        this.times = new ST<>();
        int size = (Integer) ois.readObject();
        for(int i=0; i<size; i++){
            this.times.put((Integer) ois.readObject(), (Double) ois.readObject());
        }
        this.distances = new ST<>();
        int size1 = (Integer) ois.readObject();
        for(int i=0; i<size1; i++){
            this.distances.put((Integer) ois.readObject(), (Double) ois.readObject());
        }
    }

    /**
     *
     * @param V number of vertices
     * @param ways graph ways
     *
     */

    public DoubleWeightedGraph(int V, List<Way> ways) {
        posgraphs = new ST<>();

        weightedTime = new EdgeWeightedDigraph(V);
        weightedDistance = new EdgeWeightedDigraph(V);
        restrictedNodes = new ArrayList<>();

        subgraphWeighetTime_Highway = new EdgeWeightedDigraph(V);
        subgraphWeighetDist_Highway = new EdgeWeightedDigraph(V);
        subgraphWeighetTime_Park = new EdgeWeightedDigraph(V);
        subgraphWeighetDist_Park = new EdgeWeightedDigraph(V);

        this.times= new ST<>();
        this.distances=new ST<>();
        int i=0;
        for (Way aux : ways){

            posgraphs.put(i, new ArrayList<>());

            if(aux instanceof Highway){
                addWay(aux, ((Highway) aux).isOneWay());
                subgraphWeighetTime_Highway.addEdge(new DirectedEdge(aux.getTo().id, aux.getFrom().id, aux.time(new Carro(0, null))));
                subgraphWeighetDist_Highway.addEdge(new DirectedEdge(aux.getTo().id, aux.getFrom().id, aux.getDistance()));
            }
            if(aux instanceof Park){
                subgraphWeighetTime_Park.addEdge(new DirectedEdge(aux.getTo().id, aux.getFrom().id, aux.time(new Carro(0, null))));
                subgraphWeighetDist_Park.addEdge(new DirectedEdge(aux.getTo().id, aux.getFrom().id, aux.getDistance()));
            }
            addWay(aux, false);
            i++;
        }
        this.ways=ways;

    }

    /**
     *  returns the number of Highways
     * @param ways All Ways
     *
     */

    public int getNumOfHighways(List<Way> ways){
        int count=0;
        for(Way way : ways){
            if(way instanceof Highway) count++;
        }
        return count;
    }

    /**
     *  returns the number of Parks
     * @param ways All Ways
     *
     */

    public int getNumOfParks(List<Way> ways){
        int count=0;
        for(Way way : ways){
            if(way instanceof Park) count++;
        }
        return count;
    }


    /**
     *  check if graph is connected
     * @param graph Graph
     *
     */

   public boolean isGrafoConexo(EdgeWeightedDigraph graph, int s) {



       Digraph digraph = new Digraph(graph.V());
       for(DirectedEdge directedEdge : graph.edges()){
           digraph.addEdge(directedEdge.from(), directedEdge.to());
       }
       for (DirectedEdge directedEdge: graph.edges()){
           BreadthFirstDirectedPaths breadthFirstDirectedPaths = new BreadthFirstDirectedPaths(digraph, directedEdge.from());
           for(DirectedEdge directedEdge1: graph.edges()){
               if(!breadthFirstDirectedPaths.hasPathTo(directedEdge1.from())) return false;
               if(!breadthFirstDirectedPaths.hasPathTo(directedEdge1.to())) return false;
           }
       }
       return true;

    }

    /**
     * @param locations all nodes
     * @param V number of vertices
     */

    public void shortestPathBetweenLocationsDist(ArrayList<Node> locations, int V){
        FlowNetwork flowNetwork = new FlowNetwork(V, 20);
        for(DirectedEdge directedEdge : this.weightedDistance.edges()){
            FlowEdge flowEdge = new FlowEdge(directedEdge.from(), directedEdge.to(), directedEdge.weight());
            flowNetwork.addEdge(flowEdge);
        }
        FordFulkerson fordFulkerson = new FordFulkerson(flowNetwork, locations.get(0).id, locations.get(1).id);
        System.out.println(fordFulkerson.value());
    }

    /**
     * @param locations all nodes
     * @param V number of vertices
     */

    public void shortestPathBetweenLocationsTime(ArrayList<Node[]> locations, int V){


        //Iterable<DirectedEdge> directedEdges = shortestPathRestricted(locations.get(0).id, locations.get(1).id);

        for(Node[] nodes : locations) {

            FlowNetwork flowNetwork = new FlowNetwork(V, 20);
            for (DirectedEdge edge : this.weightedDistance.edges()) {
                if (!(restrictedNodes.contains(edge.from()) || restrictedNodes.contains(edge.to()))) {
                    FlowEdge flowEdge = new FlowEdge(edge.from(), edge.to(), edge.weight());
                    flowNetwork.addEdge(flowEdge);
                }
            }
            FordFulkerson fordFulkerson = new FordFulkerson(flowNetwork, nodes[0].id, nodes[1].id);
            System.out.println("Value of flow in Nodes:" + nodes[0].idPos + ";" + nodes[1].idPos + "->" + fordFulkerson.value());
        }


    }

    /**
     * @param nodeId node restricted
     */

    public void addRestrictedNode(Integer nodeId){
        this.restrictedNodes.add(nodeId);
    }

    /**
     * gets the shortest path between 2 nodes
     * @param from node from
     * @param to node to
     */

    public Iterable<DirectedEdge> shortestPath(int from, int to){
        DijkstraSP path = new DijkstraSP(this.weightedDistance, from);
        //  System.out.println(path.hasPathTo(to));
        return path.pathTo(to);
    }
    public Iterable<DirectedEdge> shortestPathTime(int from, int to){
        DijkstraSP path = new DijkstraSP(this.weightedTime, from);
        //  System.out.println(path.hasPathTo(to));
        return path.pathTo(to);
    }

    /**
     * gets the shortest path between 2 nodes
     * @param graph graph
     * @param from node from
     * @param to node to
     */

    public Iterable<DirectedEdge> shortestPath(EdgeWeightedDigraph graph ,int from, int to){
        DijkstraSP path = new DijkstraSP(graph, from);
        //  System.out.println(path.hasPathTo(to));
        if(!path.hasPathTo(to)) throw new RuntimeException("Has no path");
        return path.pathTo(to);
    }

    /**
     * gets the shortest path between 2 nodes without the restricted nodes
     * @param from node from
     * @param to node to
     */

    public Iterable<DirectedEdge> shortestPathRestricted(int from, int to){
        EdgeWeightedDigraph aux = new EdgeWeightedDigraph(this.weightedDistance.V());
        for (DirectedEdge edge : this.weightedDistance.edges()){
            if (!(restrictedNodes.contains(edge.from()) || restrictedNodes.contains(edge.to()))) aux.addEdge(edge);
        }
        return shortestPath(aux, from, to);
    }

}
