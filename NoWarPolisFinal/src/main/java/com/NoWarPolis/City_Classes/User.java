package com.NoWarPolis.City_Classes;

import com.NoWarPolis.Util.Data;
import edu.princeton.cs.algs4.BST;
import edu.princeton.cs.algs4.DijkstraSP;
import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;




public class User implements Serializable {



  private String username;
  private String password;
  private Data birth;
  private Integer id;
  private ArrayList<Node> visitedNodes;
  private BST<Integer, PoI> visitedPoIs;
  private Hashtable<Integer, Visit> visits;
  private Node currentLocation;
  private ArrayList<Veiculo> Veiculos;
  private int numOfVisits;

  /**
   *
   * @param username - Name of the user
   * @param password - Password of the user
   * @param birth - birth date of the user
   * @param id - Id of the user
   * @param Veiculos - All vehicles that the user possesses
   * @param currentLocation - the current location of the user
   * There's an arraylist with all the visited nodes and a bst with the visited pois
   * There's also a hashtable where we can access all the visits
   */

  public User(String username, String password, Data birth, int id, ArrayList<Veiculo> Veiculos, Node currentLocation) {
    this.numOfVisits=0;
    this.currentLocation=currentLocation;
    this.username=username;
    this.password=password;
    this.birth=birth;
    this.id=id;
    this.Veiculos=Veiculos;
    this.visitedPoIs = new BST<>();
    this.visitedNodes = new ArrayList<>();
    this.visits =  new Hashtable<>();
  }




  public Integer getNumOfVisits() {
    return numOfVisits;
  }

  public void setNumOfVisits(Integer numOfVisits) {
    this.numOfVisits = numOfVisits;
  }

  public ArrayList<Node> getVisitedNodes() {
    return visitedNodes;
  }

  public void setVisitedNodes(ArrayList<Node> visitedNodes) {
    this.visitedNodes = visitedNodes;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer num){
    this.id=num;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Data getBirth() {
    return birth;
  }

  public void setBirth(Data birth) {
    this.birth = birth;
  }

  public ArrayList<Veiculo> getVeiculos() {
    return Veiculos;
  }

  public void setVeiculos(ArrayList<Veiculo> veiculos) {
    Veiculos = veiculos;
  }

  public BST<Integer, PoI> getVisitedPoIs() {
    return visitedPoIs;
  }

  public void setVisitedPoIs(BST<Integer, PoI> visitedPoIs) {
    this.visitedPoIs = visitedPoIs;
  }


  /**
   * In this method, the user will visit a Poi, it will go through all the Ways, measure the distance and time
   * and add to the hashtable of visits
   * The numofvisits helps to keep in count the number of visits this user has made
   * @param everyWayPassed An arraylist with all the ways ordered, through where the user passed to make the visit
   * @param initialTimestamp - the time it was when he started
   * @param veiculo - the vehicle he drove
   * @param poi - the poi where he is destined to go
   */

  public void visitAPoI(ArrayList<Way> everyWayPassed, Integer initialTimestamp, Veiculo veiculo, PoI poi) {
    Visit visit = new Visit();
    visit.setId(this.visits.size());
    visit.setClient(this);
    visit.setInitTime(initialTimestamp);
    visit.setFirstPosition(everyWayPassed.get(0).getFrom());
    visit.setLastPosition(everyWayPassed.get(everyWayPassed.size()-1).getTo());
    //visit.setMethod(this.getVeiculos().get(0));
    visit.setDistance(0.0);
    visit.setEndTime(0);
    visit.setPoiBeingVisited(poi);

    for(Way tempW : everyWayPassed){
      double d = visit.getDistance();
      d += tempW.getDistance();
      visit.setDistance(d);
      int time = visit.getEndTime();
      time += tempW.time();
      visit.setEndTime(time);
      visit.getTimeInNode().put(time, tempW.getTo());
    }
    this.visitedPoIs.put(visit.getEndTime(), poi);
    visit.setEndTime(visit.getEndTime()+poi.timespent);
    visit.getTimeInNode().put(visit.getEndTime(), poi.location);
    this.numOfVisits++;
    this.getVisits().put(this.numOfVisits,visit);
  }


  /**
   * returns the idPos, in long of the node
   * @param ID id to turn long
   * @param nodes the Bst with all the nodes
   * @return the idPos of node, in long
   */

  private Long intIDToLongID(Integer ID, BST<Long, Node> nodes){
    for (Long i : nodes.keys()){
      if (nodes.get(i).id.equals(ID)) return i;
    }
    return 0L;
  }


  /**
   * Gets the ways using two nodes
   * @param ways Bst with all the ways
   * @param from Node initial
   * @param to Node final
   * @return Way if it exists
   */

  private Way getWayByPoints(BST<Integer, Way> ways, Node from, Node to){
    System.out.println("Node -> " + from.idPos);
    System.out.println("Node -> " + to.idPos);
    for (Integer aux : ways.keys()){
      System.out.println("Key " + aux);
      if ((from == ways.get(aux).getFrom() && to == ways.get(aux).getTo()) || (to == ways.get(aux).getFrom() && from == ways.get(aux).getTo())){
        System.out.println("FOUND WAY");
        return ways.get(aux);
      }
    }
    System.out.println("Error");
    return null;
  }

  /**
   * Add a vehicle
   * @param tranport - vehicle to add
   */

  public void addVeiculo(Veiculo tranport){
    int i= tranport.getId();
    while (!validateVeiculUserId(tranport.getId())){
      i++;
      tranport.setId(i);
    }
      this.Veiculos.add(tranport);
  }

  /**
   * Removes a vehicle by his id and removes his user has owner
   * @param num id of vehicle
   */

  public void removeVeiculo(int num){
    int i=0;

    this.getVeiculos().get(num).setOwner(null);
    this.getVeiculos().remove(num);

    for (Veiculo aux : this.Veiculos){
      if (aux.getId()==num){
        this.Veiculos.remove(i);
        aux.setOwner(null);
        break;
      }
      i++;
    }
  }

  /**
   * validates if the vehicle exists
   * @param num vehicle id
   * @return The condition if true or false
   */

  private boolean validateVeiculUserId(int num){
    for (Veiculo aux: this.Veiculos){
      if (aux.getId() == num) return false;
    }
    return true;
  }

  /**
   * Add a new visit
   * @param visit Visit to add
   */

  public void addVisit(Visit visit){
    if(this.getVisits().containsKey(visit.getId())){
      throw new RuntimeException("ID already exists");
    }
    this.getVisits().put(visit.getId(), visit);
  }



  public Node getCurrentLocation() {
    return this.currentLocation;
  }

  public void setCurrentLocation(Node currentLocation) {
    this.currentLocation = currentLocation;
  }


  public Hashtable<Integer, Visit> getVisits() {
    return visits;
  }

  public void setVisits(Hashtable<Integer, Visit> visits) {
    this.visits = visits;
  }

  /**
   * Get the vehicle by id
   * @param id - id of the vehicle
   * @return the vehicle
   */

  public Veiculo getVeiculoById(int id){
    for (Veiculo aux : this.Veiculos){
      if(aux.getId()==id){
        return aux;
      }
    }
    return null;
  }

  /**
   *
   * @param timestamp - timestamp for the confirmation
   * @return if the timestamp is valid, it returns true
   */

  private boolean validTimestamp(double timestamp){
    for (Visit aux : this.getVisits().values()){
      if (timestamp >= aux.getInitTime() && timestamp <= aux.getEndTime()) return false;
    }
    return true;
  }

  /**
   * @param t1 initial time
   * @param t2 final time
   * @return if the pois were visited between 2 times, it adds to a list and returns it
   */

  public ArrayList<PoI> poisVisitedBetweenTimes(int t1, int t2){
    ArrayList<PoI> list = new ArrayList<>();
    for (Integer aux : this.visitedPoIs.keys()){
      if ((aux>=t1 && aux<=t2) || (aux+this.visitedPoIs.get(aux).timespent>=t1 && aux+this.visitedPoIs.get(aux).timespent<=t2) || (aux<=t1 && aux+this.visitedPoIs.get(aux).timespent>=t2)){
        list.add(this.visitedPoIs.get(aux));
      }
    }

    try {
      FileWriter writer = new FileWriter("src/Files/searches/PoIsVisitedByUser_"+t1+"_"+t2+".txt");
      writer.write("PoIs visitados entre os tempos "+t1+" e "+t2+"\n");
      for (PoI poi : list){
        writer.write("\tPoI ID: "+poi.PoiId);
      }
      writer.close();
    }
    catch(IOException e){
      e.printStackTrace();
    }

    return list;
  }

  /**
   * @param t1 initial time
   * @param t2 final time
   * @return if the pois weren't visited between 2 times, it adds to a list and returns it
   */

  public ArrayList<PoI> poisNOTVisitedBetweenTimes(double t1, double t2){
    ArrayList<PoI> list = new ArrayList<>();
    for (Integer aux : this.visitedPoIs.keys()){ //Só vê nos que o user visitou
      if (!(aux>=t1 && aux<=t2) || (aux+this.visitedPoIs.get(aux).timespent>=t1 && aux+this.visitedPoIs.get(aux).timespent<=t2) || (aux<=t1 && aux+this.visitedPoIs.get(aux).timespent>=t2)){
        list.add(this.visitedPoIs.get(aux));
      }
    }

    try {
      FileWriter writer = new FileWriter("src/Files/searches/PoIsNotVisitedByUser_"+t1+"_"+t2+".txt");
      writer.write("PoIs não visitados entre os tempos "+t1+" e "+t2+"\n");
      for (PoI poi : list){
        writer.write("\tPoI ID: "+poi.PoiId);
      }
      writer.close();
    }
    catch(IOException e){
      e.printStackTrace();
    }

    return list;
  }

  public ArrayList<Node> getOverLotatedNodes(double timestamp, BST<Long, Node> nodes){
    ArrayList<Node> aux = new ArrayList<>();
    for (Long id : nodes.keys()){
      if (nodes.get(id).isOverLotated(timestamp)) aux.add(nodes.get(id));
    }
    return aux;
  }

  public ArrayList<Node> getInterruptedNodes(double timestamp, BST<Long, Node> nodes){
    ArrayList<Node> aux = new ArrayList<>();
    for (Long id : nodes.keys()){
      if (nodes.get(id).isInterrupted(timestamp)) aux.add(nodes.get(id));
    }
    try {
      FileWriter writer = new FileWriter("src/Files/searches/Interrupted_Nodes_" + timestamp + ".txt");
      writer.write("Nodes interrompidos na timestamp "+timestamp+"\n");
      for (Node node : aux){
        writer.write("\tNode ID: "+node.idPos);
      }
      writer.close();
    }
    catch(IOException e){
      e.printStackTrace();
    }



    return aux;
  }

  public Iterable<DirectedEdge> shortestPath(EdgeWeightedDigraph graph, int from, int to){
    DijkstraSP path = new DijkstraSP(graph, from);
  //  System.out.println(path.hasPathTo(to));
    return path.pathTo(to);
  }

  public Iterable<DirectedEdge> shortestPathRestricted(ArrayList<Integer> restrictedNodes, EdgeWeightedDigraph graph, int from, int to){
    EdgeWeightedDigraph aux = new EdgeWeightedDigraph(graph.V());
    for (DirectedEdge edge : graph.edges()){
      if (!(restrictedNodes.contains(edge.from()) || restrictedNodes.contains(edge.to()))) aux.addEdge(edge);
    }
    return shortestPath(aux, from, to);
  }

  @Override
  public String toString() {
    return "User{" +
            "username='" + username + '\'' +
            ", password='" + password + '\'' +
            ", birth=" + birth +
            ", id=" + id +
            '}';
  }
}
