package com.NoWarPolis;

import com.NoWarPolis.City_Classes.Points_Of_Interest.Abastecimento;
import com.NoWarPolis.City_Classes.Points_Of_Interest.Crossing;
import com.NoWarPolis.City_Classes.Points_Of_Interest.Semaforo;
import com.NoWarPolis.City_Classes.Points_Of_Interest.Stop_Signal;
import com.NoWarPolis.City_Classes.Vehicles.Carro;
import com.NoWarPolis.City_Classes.Vehicles.Pedestre;
import com.NoWarPolis.City_Classes.Vehicles.Trotinete;
import com.NoWarPolis.Util.Data;
import edu.princeton.cs.algs4.*;
import com.NoWarPolis.City_Classes.*;


import java.io.*;
import java.util.*;


public class City implements Serializable{
  /**
   * Attributes
   */

  /**
   * There is a graph, city map, which contains the vertices and edges for the paths
   * A list of ways that have 2 nodes
   * A Btree with the timestamps
   * A BST that contains all the city Nodes, can be search with the Long idPos
   * An Hashtable with a string, the type of etiqueta, abastecimento per example, and an array list with all idPos of the nodes in which the etiquetas are contained
   * An Hashtable with an Integer, the ID of a person, and the corresponding User
   * An ArrayList with all PointsOfInterest, "etiquetas"
   */



  public DoubleWeightedGraph cityMap;
  //public
  public List<Way> ways;
  public BTree<Integer, Long> timestamps;
  public BST<Long, Node> cityNodes;
  public Hashtable<String, ArrayList<Long>> etiquetas;
  public Hashtable<Integer, User> users;
  public ArrayList<PoI> PointsOfInterest;


  /**
   *
   * @param type 1- reads binary; 2- reads text
   */

  public City(int type) {

    this.timestamps= new BTree<>();
    this.cityNodes = new BST<>();
    this.ways = new ArrayList<>();
    this.users = new Hashtable<>();
    this.etiquetas = new Hashtable<>();
    this.PointsOfInterest = new ArrayList<>();



    if(type == 2) {   //Read by text
      readNodes();
      readWays();
      readPoi();
      readUsers();
      readVehicle();
      readVisits();

    } else if(type == 1){   //Read by Bin
      readBinNodes();
      readBinWays();
      readBinPoi();
      readBinUsers();
    }

  }



  private void readBinNodes(){
  }
  private void readBinWays(){
  }
  private void readBinPoi(){
  }
  private void readBinUsers(){
  }

  /**
   * reads visits from file
   */

  private void readVisits(){
    try{
      File read = new File("src/main/java/com/NoWarPolis/Files/dataset1_visits.txt");
      Scanner reader = new Scanner(read);
      reader.nextLine();
      for (int i=0;reader.hasNextLine();i++){
        String data = reader.nextLine();
        String[] data1 = data.split(",");
        User user = users.get(Integer.parseInt(data1[1]));
        Visit visit = initVisit(user, data);
        user.getVisits().put(Integer.parseInt(data1[0]),visit);
        user.getVisitedPoIs().put(visit.getPoiBeingVisited().PoiId, visit.getPoiBeingVisited());

      }
    } catch (FileNotFoundException e){
      System.out.println("Error: READING FILE");
      e.printStackTrace();
    }
  }

  /**
   * reads vehicles from file
   */

  private void readVehicle(){

    try{
      File read = new File("src/main/java/com/NoWarPolis/Files/dataset1_vehicles.txt");
      Scanner reader = new Scanner(read);

      for (int i=0;reader.hasNextLine();i++){
        String[] data = reader.nextLine().split(",");
        if(data[0].equals("car")) getUsers().get(Integer.parseInt(data[2])).addVeiculo(new Carro(Integer.parseInt(data[1]), getUsers().get(Integer.parseInt(data[2]))));
        if(data[0].equals("trotinete")) getUsers().get(Integer.parseInt(data[2])).addVeiculo(new Trotinete(Integer.parseInt(data[1]), getUsers().get(Integer.parseInt(data[2]))));
        if(data[0].equals("pedestre")) getUsers().get(Integer.parseInt(data[2])).addVeiculo(new Pedestre(Integer.parseInt(data[1]), getUsers().get(Integer.parseInt(data[2]))));

      }
    } catch (FileNotFoundException e){
      System.out.println("Error: READING FILE");
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }



  /**
   *
   * Reads from the file all the existent Poi's
   * Differentiates between all the extensions of Poi's
   *
   */

  private void readPoi(){

    try{
      File read = new File("src/main/java/com/NoWarPolis/Files/dataset1_PoI.txt");
      Scanner reader = new Scanner(read);
      reader.nextLine();

      for (int i=0;reader.hasNextLine();i++){
        String[] data = reader.nextLine().split(",");
        if(data[0].equals("abastecimento")){
          addEtiqueta_Node(new Abastecimento(searchNodebyId(Long.parseLong(data[2])), Integer.parseInt(data[1])));
        }
        if(data[0].equals("semaforo")){
          addEtiqueta_Node(new Semaforo(searchNodebyId(Long.parseLong(data[2])), Integer.parseInt(data[1])));
        }
        if(data[0].equals("crossing")){
          addEtiqueta_Node(new Crossing(searchNodebyId(Long.parseLong(data[2])), Integer.parseInt(data[1])));
        }
        if(data[0].equals("stop_signal")){
          addEtiqueta_Node(new Stop_Signal(searchNodebyId(Long.parseLong(data[2])), Integer.parseInt(data[1])));
        }
      }
    } catch (FileNotFoundException e){
      System.out.println("Error: READING FILE");
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  /**
   * Reads all the users from a file
   * Each user has Visits, if they truly have a visit, they will be separated by ",Visits:"
   * At the end of each user, it has the "NumOfvisits" this will allow us to see how many travels there were since the beginning of the city
   * Plus, the VisitId will be the NumOfVisits + 1
   */

  private void readUsers(){
    try{
      File read = new File("src/main/java/com/NoWarPolis/Files/dataset1_users.txt");
      Scanner reader = new Scanner(read);
      reader.nextLine();
      int i;
      for (i=0;reader.hasNextLine();i++){
        String tempData = reader.nextLine();
        String[] visits = tempData.split(",Visits:");
        String[] data = tempData.split(",");
        String[] birth = data[3].split("/");
        String[] numofvisits = tempData.split(",NumOfVisits:");


        int dd = Integer.parseInt(birth[0]), mm = Integer.parseInt(birth[1]), yyyy = Integer.parseInt(birth[2]);
        if(data[4].isEmpty()) {
          User n1 = new User(data[1], data[2], new Data(dd, mm, yyyy), Integer.parseInt(data[0]), new ArrayList<>(), searchNodebyId(Long.parseLong(data[5])));
          if(!numofvisits[1].equals("0")) n1.setNumOfVisits(Integer.parseInt(numofvisits[1]));
          int size=visits.length;
          for (int j=0; j<size-1; j++) {
            Visit v1 = initVisit(n1, visits[j+1]);
            n1.addVisit(v1);
          }
          this.getUsers().put(Integer.parseInt(data[0]), n1);

        }



      }
    } catch (FileNotFoundException e){
      System.out.println("Error: READING FILE");
      e.printStackTrace();
    }
  }

  /**
   *
   * A complementary method of "readUser()"
   * Fully initializes the Visit, with all the information needed
   *
   * @param n1 - The user where the visit will be added
   * @param visit - The string with the information of the visit
   * @return - The visit fully initialized
   */


  private Visit initVisit(User n1, String visit){

    String[] data = visit.split(",");
    Visit v1 = new Visit();

      v1.setId(Integer.parseInt(data[0]));

      v1.setClient(n1);
      v1.setInitTime(Integer.parseInt(data[2]));
      v1.setEndTime(Integer.parseInt(data[3]));
      v1.setFirstPosition(this.getCityNodes().get(Long.parseLong(data[4])));
      v1.setLastPosition((this.getCityNodes().get(Long.parseLong(data[5]))));
      v1.setDistance(Double.parseDouble(data[6]));
      v1.setPoiBeingVisited(this.getPoiByID(Integer.parseInt(data[7])));
      n1.getVisitedPoIs().put(Integer.parseInt(data[7]), this.getPoiByID(Integer.parseInt(data[7])));
      v1.setMethod(null);
      int size=data.length;
      for(int i=9; i<size; i++) {

        String[] temp = data[i].split(";");
        if(temp[0].contains("NumOfVisits:")) break;
        v1.getTimeInNode().put(Integer.parseInt(temp[0]), this.getCityNodes().get(Long.parseLong(temp[1])));

      }


    return v1;
  }

  /**
   * Reads all nodes from a file and initializes the graph of city with the number of nodes
   */
  private int aux;


  private void readNodes(){
    try{
      File read = new File("src/main/java/com/NoWarPolis/Files/dataset1_nodes.txt");
      Scanner reader = new Scanner(read);
      reader.nextLine();
      int i;
      for (i=0;reader.hasNextLine();i++){
        String[] data = reader.nextLine().split(",");
        Node n1;
        if(data.length==3) n1 = new Node(i, Long.parseLong(data[0]),Double.parseDouble(data[1]), Double.parseDouble(data[2]),"");
        else n1 = new Node(i, Long.parseLong(data[0]),Double.parseDouble(data[1]), Double.parseDouble(data[2]),data[3]);
        this.getCityNodes().put(Long.parseLong(data[0]), n1);
      }
      aux=i;

    } catch (FileNotFoundException e){
      System.out.println("Error: READING FILE");
      e.printStackTrace();
    }
  }

  /**
   * Reads all the ways from files and defines them
   */

  private void readWays(){
    try{
      File read = new File("src/main/java/com/NoWarPolis/Files/dataset1_ways_nodepairs.txt");
      Scanner reader = new Scanner(read);
      reader.nextLine();
      int i=0;
      while (reader.hasNextLine()){
        defineWays(reader.nextLine(), i);
        i++;
      }
      this.cityMap = new DoubleWeightedGraph(aux, this.ways);
      for (Way w : this.ways) {
        w.setId(getcityMapDistance().weightedTime.E());

      }
    } catch (FileNotFoundException e){
      System.out.println("Error: READING FILE");
      e.printStackTrace();
    }
  }




  /**
   * Getters and setters
   */

  public DoubleWeightedGraph getcityMapDistance() {
    return cityMap;
  }

  public void setcityMapDistance(DoubleWeightedGraph cityMap) {
    this.cityMap = cityMap;
  }

  public List<Way> getWays() {
    return ways;
  }

  public void setWays(List<Way> ways) {
    this.ways = ways;
  }

  public BTree<Integer, Long> getTimestamps() {
    return timestamps;
  }

  public void setTimestamps(BTree<Integer, Long> timestamps) {
    this.timestamps = timestamps;
  }

  public BST<Long, Node> getCityNodes() {
    return cityNodes;
  }

  public void setCityNodes(BST<Long, Node> cityNodes) {
    this.cityNodes = cityNodes;
  }

  public Hashtable<String, ArrayList<Long>> getEtiquetas() {
    return etiquetas;
  }

  public void setEtiquetas(Hashtable<String, ArrayList<Long>> etiquetas) {
    this.etiquetas = etiquetas;
  }

  public Hashtable<Integer, User> getUsers() {
    return users;
  }

  public void setUsers(Hashtable<Integer, User> users) {
    this.users = users;
  }

  public ArrayList<PoI> getPointsOfInterest() {
    return PointsOfInterest;
  }

  public void setPointsOfInterest(ArrayList<PoI> pointsOfInterest) {
    PointsOfInterest = pointsOfInterest;
  }


  /**
   * Inserts Node
   * @param id - Id of the Node
   * @param n1 - The Node
   */

  public void insertNode(Long id, Node n1){
    this.getCityNodes().put(id, n1);
  }

  /**
   * adds an poi to the city
   * If there's already a poi extension existent, like "abastecimento", it adds a new poi to that, if not, creates a new
   * @param poi - poi to add to city
   */

  public void addEtiqueta_Node(PoI poi) throws Exception{
    if(!(poi.location.id<this.cityMap.weightedTime.V())){
      throw new Exception("ERROR: VERTICES OVERLOAD");
    }
    if((this.getEtiquetas().containsKey(poi.name) && getEtiquetas().get(poi.name).contains(poi.location.idPos))){
      throw new Exception("ERROR: POINT OF INTEREST ALREADY EXISTS");
    }

    /*if(this.getEtiquetas().containsKey(poi.name)){
      this.getEtiquetas().get(poi.name).add(poi.location.idPos);
      addPoI(poi);
      return;
    }
    this.getEtiquetas().put(poi.name, new ArrayList<>());
    this.getEtiquetas().get(poi.name).add(poi.location.idPos);*/
    addPoI(poi);

  }

  /**
   * Adds a new user, if there's already a user with that ID, it gives error
   * @param person - person to be added
   */
  public void addUser(User person){
    if(this.getUsers().containsKey(person.getId())){
      throw new RuntimeException("ID Already exists, remove the user first");
    } else {
      this.users.put(person.getId(), person);
    }
  }


  /**
   * It removes a poi
   * Removes from both list of the hashtable and the arraylist of all poi
   * @param poi - poi to be removed
   */

  public void removePointOfInterest(PoI poi){
    this.getCityNodes().delete(poi.location.idPos);
    getPointsOfInterest().remove(searchPointOfInterest(poi));
    getEtiquetas().get(poi.name).remove(poi.vertice);
    if(getEtiquetas().get(poi.name).isEmpty()){
      getEtiquetas().remove(poi.name);
    }
    for(User user : this.getUsers().values()){
      for(Visit visit : user.getVisits().values()){
        if(visit.getPoiBeingVisited().equals(poi)){
          user.getVisits().remove(visit.getId());
        }
      }
    }

  }

  /**
   * Removes a node from the city, if it exists
   * @param key - id of the node to be removed
   */
  public void deleteCityNodes(Long key) {
    if(!this.getCityNodes().contains(key)){
      throw new RuntimeException("No node found to be removed");
    }
    this.getCityNodes().delete(key);
  }

  /**
   * Removes a user from the city, if it exists
   * @param num - id of the user to be removed
   */
  public void removeUser(int num){
    if(!this.getUsers().containsKey(num)){
      throw new RuntimeException("No user found to be removed");
    }
    this.getUsers().remove(num);
  }

  /**
   * Edit function:
   * @param num - Userid
   * @param name - Name to be changed
   */

  public void editUserName(int num, String name){
    searchUserById(num).setUsername(name);
  }

  /**
   * Edit function:
   * @param num - Userid
   * @param date - date to be changed
   */

  public void editUser(int num, Data date){
    searchUserById(num).setBirth(date);
  }

  /**
   * Edit function:
   * @param num - Userid
   * @param pass - Password to be changed
   */

  public void editUserPassword(int num, String pass){
    searchUserById(num).setPassword(pass);
  }


  /**
   * Search the Poi
   * @param poi - poi to be searched
   * @return the founded Poi
   */
  public PoI searchPointOfInterest(PoI poi){
    if(!getPointsOfInterest().contains(poi)){
      System.out.println("No Poi found");
      return null;
    }
    return getPointsOfInterest().get(getPointsOfInterest().indexOf(poi));
  }

  /**
   * Search by coordinates
   * @param lat - latitude
   * @param lon - longitude
   * @return - the node found
   */
  public Node searchNodebyCoordinates(int lat, int lon) {
    for (Long i : getCityNodes().keys()) {
      if (this.getCityNodes().get(i).lat == lat && this.getCityNodes().get(i).lon == lon) {
        return getCityNodes().get(i);
      }
    }
    return null;
  }

  /**
   * Search node by id
   * @param id - id of node
   * @return - the node found
   */

  public Node searchNodebyId(Long id) {
    if(this.getCityNodes().contains(id)) return this.getCityNodes().get(id);
    return null;


  }

  /**
   * Searches the user by id
   * @param num - id to be found
   * @return User found
   */
  public User searchUserById(int num){
    if(!this.getUsers().containsKey(num)){
      throw new RuntimeException("User doesn't exists");
    }
    return this.getUsers().get(num);
  }

  /**
   * It defines the edges by the ways so the city has all the paths
    * @param way - two nodes with much information, and defines the graph
   */

  public void defineEdge(Way way){

  //  getcityMapDistance().addEdge(way.getFrom().id, way.getTo().id);
    this.cityMap.weightedDistance.addEdge(new DirectedEdge(way.getFrom().id, way.getTo().id, way.getDistance()));
    this.cityMap.weightedTime.addEdge(new DirectedEdge(way.getFrom().id, way.getTo().id, way.time(new Carro(0, null))));

    way.setId(getcityMapDistance().weightedTime.E());
    this.getWays().add(way);
  }

  /**
   * @param poi - the base poi for the search
   * @param t1 - initial time
   * @param t2 - final time
   * @return - a list with all users that visited a certain poi between two given times, if any weren't found, returns null
   */

  public ArrayList<User> usersThatVisitedPoIBetweenTimes(PoI poi, int t1, int t2){
    ArrayList<User> users = new ArrayList<>();
    for(User user : this.getUsers().values()){
      for (PoI userPoi : user.poisVisitedBetweenTimes(t1, t2)){
        if (poi==userPoi) {
          users.add(user);
        }
      }
    }
    if(users.isEmpty()) return null;

    try {
      FileWriter writer = new FileWriter("src/main/java/com/NoWarPolis/Files/searches/UsersThatVisitedPoIBetweenTimes"+t1+"_"+t2+".txt");
      writer.write("Users que visitaram o PoI "+poi.PoiId + " entre" +t1+" e "+t2+"\n");
      for (User user : users){
        writer.write("\tUsername: "+user.getUsername());
      }
      writer.close();
    }
    catch(IOException e){
      e.printStackTrace();
    }

    return users;
  }

  /**
   * @param start - initial time
   * @param end - final time
   * @return - the pois that weren't visited between these times
   */

  public ArrayList<PoI> PoIsNOTvisitedBetweenTimes(int start, int end){
    ArrayList<PoI> list = new ArrayList<>();
    for (PoI aux : this.PointsOfInterest){
      if (aux.usersThatVisitedBetweenTimes(start, end).isEmpty()){
        list.add(aux);
      }
    }

    try {
      FileWriter writer = new FileWriter("src/main/java/com/NoWarPolis/Files/searches/PoIsNotVisitedBetweenTimes"+start+"_"+end+".txt");
      writer.write("PoIs não visitados entre os tempos "+start+" e "+end+"\n");
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
   * It adds to a list all those users, if there's no more than 5 users, returns the list, if there's more, it removes
   * the users with less visited pois between times with the help of the method
   * @param start - initial time
   * @param end - final time
   * @return - the top five users that visited the bigger number of pois between times
   */


  public ArrayList<User> TopFiveUsers_VisitedBiggerNumberOfPoIsBetweenTimes(int start, int end){

    ArrayList<User> list = new ArrayList<>();
    for (User user : this.getUsers().values()){
      if (!user.poisVisitedBetweenTimes(start, end).isEmpty())
      list.add(user);
    }
    if (list.size()<=5) return list;

    while (list.size()>5){
      list.remove(getUserWithLessVisitedPoisBetweenTimes(list, start, end));
    }

    try {
      FileWriter writer = new FileWriter("src/main/java/com/NoWarPolis/Files/searches/Top_5_Users_with_more_visits"+start+"_"+end+".txt");
      writer.write("Top 5 users que visitaram mais PoIs entre "+start+" e "+end+"\n");
      for (User user : list){
        writer.write("\tUsername: "+user.getUsername());
      }
      writer.close();
    }
    catch(IOException e){
      e.printStackTrace();
    }

    return list;
  }

  /**
   * An auxiliary method of the TopFiveUsers_VisitedBiggerNumberOfPoIsBetweenTimes(int start, int end)
   * @param users - Base list of users for comparison
   * @param start - initial time
   * @param end - final time
   * @return the user with less visited pois between times
   */

  private User getUserWithLessVisitedPoisBetweenTimes(ArrayList<User> users, int start, int end){
    User minor = null;
    int cont=0;
    for (User id : users){
      if (cont==0){
        minor=id;
        cont++;
      }
      if (id.poisVisitedBetweenTimes(start, end).size()<minor.poisVisitedBetweenTimes(start, end).size()){
        minor=id;
      }
    }
    return minor;
  }

  /**
   * It adds to a list all those pois, if there's no more than 5 pois, returns the list, if there's more, it removes
   * the pois with less visits between times
   * @param start - initial time
   * @param end - final time
   * @return - the top five pois with most visits between times
   */


  public ArrayList<PoI> top5MostVisitedPoisBetweenTimes(int start, int end){
    ArrayList<PoI> list = new ArrayList<>();
    //
  for (PoI poi : this.getPointsOfInterest()){
    if (!poi.usersThatVisitedBetweenTimes(start, end).isEmpty()){
      list.add(poi);
    }
  }
    if (list.size()<=5) return list;
    while (list.size()>5){
      list.remove(getPoiLessVisitedBetweenTimes(PointsOfInterest, start, end));
    }

    try {
      FileWriter writer = new FileWriter("src/Files/searches/Top_5_Most_Visited_Pois_"+start+"_"+end+".txt");
      writer.write("Top 5 PoIs mais visitados entre os tempos "+start+" e "+end+"\n");
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
   * An auxiliary method of the top5MostVisitedPoisBetweenTimes(int start, int end)
   * @param pois - Base list of pois for comparison
   * @param start - initial time
   * @param end - final time
   * @return the poi with less visites between times
   */

  private PoI getPoiLessVisitedBetweenTimes(ArrayList<PoI> pois, int start, int end){
    PoI minor = null;
    int cont=0;
    for (PoI id : pois){
      if (cont==0){
        minor=id;
        cont++;
      }
      if (id.usersThatVisitedBetweenTimes(start, end).size()<minor.usersThatVisitedBetweenTimes(start, end).size()){
        minor=id;
      }
    }

    return minor;
  }

  /**
   * @param poi poi to search
   * @return the list with all the nodes with this poi
   */

  public ArrayList<Node> nodesThatHavePoI(String poi){
    ArrayList<Node> nodes = new ArrayList<>();
    for (Long i : etiquetas.get(poi)){
      nodes.add(cityNodes.get(i));
    }
    return nodes;
  }

  /**
   * This method defines the ways
   * When reading from a file, this file will parse the information and define the way
   * if it has an address, it creates a "park" which is a place with a proper adress
   * If its an highway, like a road, it creates with different param
   * @param s - String read from the file
   * @param i - the index of the way
   */


  private void defineWays(String s, int i){
    String[] data = s.split(",");
    int size = data.length;
    if(data[4].contains("addr:")){
      Way w = new Park(i, Long.parseLong(data[0]), getCityNodes().get(Long.parseLong(data[1])), getCityNodes().get(Long.parseLong(data[2])), Double.parseDouble(data[3]));
      for(int j=0; j<size; j++){

        w.entered(data, j);
      }

      w.setAverageVelocity(50.0);
      this.ways.add(w);

    }
    for(String temp : data){
      if(temp.equals("highway")){
        Way w = new Highway(i, Long.parseLong(data[0]), getCityNodes().get(Long.parseLong(data[1])), getCityNodes().get(Long.parseLong(data[2])), Double.parseDouble(data[3]));
        for(int j = 0; j<size; j++){
          w.entered(data, j);
        }
        this.ways.add(w);
      }
    }


  }

  /**
   * gets a PoI by it's ID
   */

  public PoI searchPoIByID(int id){
    for(PoI poI : this.getPointsOfInterest()){
      if(poI.PoiId==id){
        return poI;
      }
    }
    return null;
  }

  /**
   * It prints in the screen the information of that timestamp, in other words, what is happening in that second
   * where the user is more specifically
   * @param timestamp the timestamp for search
   */


  public void now(Integer timestamp){
    for(User user : this.getUsers().values()){
      int sizeVisit = user.getVisits().size();

      for(Visit visits : user.getVisits().values())

        if (visits.getTimeInNode().contains(timestamp)) {
          System.out.println(visits.getTimeInNode().get(timestamp));
          break;
        } else if(visits.getTimeInNode().ceiling(timestamp).equals(visits.getTimeInNode().max())){
          System.out.println(visits.getPoiBeingVisited());

        }else {
          int t1 = visits.getTimeInNode().ceiling(timestamp);
          int t2 = visits.getTimeInNode().floor(timestamp);
          System.out.println(getWayBy2Nodes(visits.getTimeInNode().get(t1), visits.getTimeInNode().get(t2)));
          break;
        }
    }

  }

  /**
   * search a way with 2 nodes
   * @param from first position
   * @param to second position
   * @return the way found
   */

  private Way getWayBy2Nodes(Node from, Node to){
    int size = this.getWays().size();
    for(int i=0; i<size; i++){
      if(this.getWays().get(i).getFrom().equals(from) && this.getWays().get(i).getTo().equals(to) || this.getWays().get(i).getFrom().equals(to) && this.getWays().get(i).getTo().equals(from)){
        return this.getWays().get(i);
      }
    }
    return null;
  }

  /**
   * Search a poi by his ID
   * @param id - id to find
   * @return - the poi found
   */

  private PoI getPoiByID(Integer id){
    for (PoI poi : this.PointsOfInterest){
      if (poi.PoiId.equals(id)) return poi;
    }
    return null;
  }

  /**
   * Adds a poi to every place where he's needed
   * @param poi - poi to add
   */

  public void addPoI(PoI poi){
    if(!this.getEtiquetas().containsKey(poi.name)){
      this.getEtiquetas().put(poi.name, new ArrayList<>());
    }
    this.getEtiquetas().get(poi.name).add(poi.location.idPos);
    this.PointsOfInterest.add(poi);
    //searchNodebyId(poi.location.idPos).pointsOfInteress.add(poi);
    this.getCityNodes().get(poi.location.idPos).pointsOfInteress.add(poi);
  }

  /**
   * File to be updated by the end of the program
   */

  public void updateFile(int type) {
    if(type == 1) {
      writeBin();
    }
    if(type == 2){
      writeUser();
      writePoi();
      writeVehicles();
      writeVisits();
    }
  }


  private void writeBin(){

    try {
      File file = new File("src/main/java/com/NoWarPolis/Files/dataset1_graphAndSTs.bin");
      FileOutputStream fos = new FileOutputStream(file);
      ObjectOutputStream oos = new ObjectOutputStream(fos);
      writeBinGraph(oos, this.cityMap);
      oos.flush();
      oos.close();



    } catch (Exception e){
      e.printStackTrace();
    }

  }

  public void writeBinGraph(ObjectOutputStream oos, DoubleWeightedGraph graph) throws IOException {

    writeBinlist(oos, graph.restrictedNodes);
    writeBinOneGraph(oos, graph.weightedTime);
    writeBinOneGraph(oos, graph.weightedDistance);
    writeBinOneGraph(oos, graph.subgraphWeighetTime_Highway);
    writeBinOneGraph(oos, graph.subgraphWeighetDist_Highway);
    writeBinOneGraph(oos, graph.subgraphWeighetDist_Park);
    writeBinOneGraph(oos, graph.subgraphWeighetTime_Park);
    writeBinOneSt(oos, graph.times);
    writeBinOneSt(oos, graph.distances);
    writeBinOneStList(oos, graph.posgraphs);

  }

  private void writeBinlist(ObjectOutputStream oos, ArrayList<Integer> arrayList) throws IOException {
    oos.writeObject(arrayList.size());
    for(Integer integer : arrayList){
      oos.writeObject(integer);
    }
  }

  private void writeBinOneStList(ObjectOutputStream oos, ST<Integer, ArrayList<Boolean>> st) throws IOException {
    oos.writeObject(st.size());
    for(Integer keys : st){
      oos.writeObject(keys);
      oos.writeObject(st.get(keys).size());
      for(Boolean b : st.get(keys)){
        oos.writeObject(b);
      }
    }
  }

  private void writeBinOneSt(ObjectOutputStream oos, ST<Integer, Double> st) throws IOException {
    oos.writeObject(st.size());
    for(Integer keys : st){
      oos.writeObject(keys);
      oos.writeObject(st.get(keys));
    }
  }

  private void writeBinOneGraph(ObjectOutputStream oos, EdgeWeightedDigraph graph) throws IOException {
    oos.writeObject(graph.V());
    oos.writeObject(graph.E());

    for(DirectedEdge directedEdge : graph.edges()){
      oos.writeObject(directedEdge.from());
      oos.writeObject(directedEdge.to());
      oos.writeObject(directedEdge.weight());
    }
  }




  private void writeBinNode(ObjectOutputStream oos, Node node) throws IOException {
    oos.writeObject(node.id);
    oos.writeObject(node.idPos);
    oos.writeObject(node.lat);
    oos.writeObject(node.lon);
    for(PoI poI : node.pointsOfInteress){
      writeBinPoI(oos, poI);
    }

    oos.writeObject(node.interruptions);

  }

  private void writeBinBSTUser(ObjectOutputStream oos, BST<Integer, ArrayList<User>> objectBST) throws IOException {
    for(Integer integer : objectBST.keys()){
      oos.writeObject(integer);
      oos.writeObject(objectBST.get(integer));
    }
  }

  private void writeBinPoI(ObjectOutputStream oos, PoI poI) throws IOException {
    oos.writeObject(poI.PoiId);
    oos.writeObject(poI.name);
    oos.writeObject(poI.vertice);
    oos.writeObject(poI.timespent);
    writeBinBSTUser(oos, poI.usersVisited);
    writeBinNode(oos, poI.location);
  }

  private void writeBinBSTPoI(ObjectOutputStream oos, BST<Integer, PoI> objectBST) throws IOException {
    for(Integer integer : objectBST.keys()){
      oos.writeObject(integer);
      //oos.writeObject(objectBST.get(integer));
      oos.writeObject(objectBST.get(integer).PoiId);
      oos.writeObject(objectBST.get(integer).name);
      oos.writeObject(objectBST.get(integer).vertice);
      oos.writeObject(objectBST.get(integer).timespent);
      writeBinBSTUser(oos, objectBST.get(integer).usersVisited);
      writeBinNode(oos, objectBST.get(integer).location);
    }
  }


  /**
   * Writes all the pois to the file
   */

  private void writePoi(){
    try {
      FileWriter write = new FileWriter("src/main/java/com/NoWarPolis/Files/dataset1_PoI.txt");
      write.write(this.getPointsOfInterest().size() + "\n");
      for(PoI poI : this.getPointsOfInterest()){
        write.write(poI.toString());
        write.write("\n");
      }

      write.close();
      System.out.println("Successfully wrote");

    } catch (IOException e){
      System.out.println("Error");
      e.printStackTrace();
    }

  }

  private void writeVehicles(){

    try {
      FileWriter write = new FileWriter("src/main/java/com/NoWarPolis/Files/dataset1_vehicles.txt");

      for(User user : this.getUsers().values()){
        for(Veiculo veiculo : user.getVeiculos()){
          if(veiculo instanceof Carro)write.write(((Carro)veiculo).toString());
          if(veiculo instanceof Trotinete)write.write(((Trotinete)veiculo).toString());
          if(veiculo instanceof Pedestre)write.write(((Pedestre)veiculo).toString());
          write.write("\n");
        }

      }
      write.close();
      System.out.println("Successfully wrote");

    } catch (IOException e){
      System.out.println("Error");
      e.printStackTrace();
    }
  }


  /**
   * writes every user and their visits to the file
   */

  private void writeVisits(){
    try {
      FileWriter write = new FileWriter("src/main/java/com/NoWarPolis/Files/dataset1_visits.txt");
      write.write(this.getUsers().size() + "\n");
      for(User user : this.getUsers().values()){

        for (Visit visit : user.getVisits().values()){
          write.write(visitToString(user, visit));
          for(Integer keyNode : visit.getTimeInNode().keys()){
            write.write( "," + keyNode + ";" + visit.getTimeInNode().get(keyNode).idPos);
          }
          write.write("\n");
        }


      }
      write.close();
      System.out.println("Successfully wrote");

    } catch (IOException e){
      System.out.println("Error");
      e.printStackTrace();
    }
  }

  /**
   * writes user in file
   */

  private void writeUser(){

    try {
      FileWriter write = new FileWriter("src/main/java/com/NoWarPolis/Files/dataset1_users.txt");
      write.write(this.getUsers().size() + "\n");
      for(User user : this.getUsers().values()){
        write.write(user.getId() + "," + user.getUsername() + "," + user.getPassword() + "," + user.getBirth().toString() + "," + "," + user.getCurrentLocation().idPos + ",");


        /*for (Visit visit : user.getVisits().values()){
          write.write( ",Visits:");
          write.write(visitToString(user, visit));
          for(Integer keyNode : visit.getTimeInNode().keys()){
            write.write( "," + keyNode + ";" + visit.getTimeInNode().get(keyNode).idPos);
          }

        }*/
        write.write( ",NumOfVisits:"+user.getNumOfVisits());
      write.write("\n");



      }
      write.close();
      System.out.println("Successfully wrote");

    } catch (IOException e){
      System.out.println("Error");
      e.printStackTrace();
    }
  }

  /**
   * A "to string" to write the visit to the file
   * @param user - the user
   * @param visit - the visit
   * @return - A String
   */

  private String visitToString(User user, Visit visit){
    return visit.getId() + "," +
            user.getId() + "," +
            visit.getInitTime() + "," +
            visit.getEndTime() + "," +
            visit.getFirstPosition().idPos + "," +
            visit.getLastPosition().idPos + "," +
            visit.getDistance() + ","  +
            visit.getPoiBeingVisited().PoiId + ",";
            //visit.getMethod().getId();
  }

  public ArrayList<Way> getListOfWays(int index) {

    if (index == 0) {
      ArrayList<Way> test = new ArrayList<>();
      test.add(this.getWays().get(1));
      test.add(this.getWays().get(2));
      test.add(this.getWays().get(3));
      test.add(this.getWays().get(4));
      return test;
    } else if (index == 1) {
      ArrayList<Way> test = new ArrayList<>();
      test.add(this.getWays().get(4));
      test.add(this.getWays().get(1));
      test.add(this.getWays().get(2));
      return test;
    } else if (index == 2) {
      ArrayList<Way> test = new ArrayList<>();
      test.add(this.getWays().get(7));
      test.add(this.getWays().get(8));
      test.add(this.getWays().get(19));
      return test;
    }
    return null;
  }

  /**
   * gets all the nodes that have a certain type of PoI
   */

  public ArrayList<Node> NodesThatHavePoi(String poi){
    ArrayList<Node> aux = new ArrayList<>();
    for (Long i : this.getEtiquetas().get(poi)){
      aux.add(this.cityNodes.get(i));
    }
    return aux;
  }

  /**
   * gets the closest sems
   */

  public ArrayList<PoI> closestSem(Node node) throws Exception {

    BST<Double, Node> table = new BST<>();
    ArrayList<PoI> toReturn = new ArrayList<>();
    double minor=0;


    for (Node aux : NodesThatHavePoi("semaforo")){
      DijkstraSP path = new DijkstraSP(cityMap.weightedDistance, node.id);
      double temp=0;
      if (!path.hasPathTo(aux.id)) continue;
      for (DirectedEdge i : path.pathTo(aux.id)){
        temp+=i.weight();
      }
      table.put(temp, aux);
    }

    for (int i=0; i<3; i++){
      if (!table.isEmpty()) {
        toReturn.add(searchPoIByNode(table.get(table.min()), "semaforo"));
        table.deleteMin();
      }
    }


    return toReturn;
  }

  /**
   *
   * @param node node
   * @param poi poi
   * @return the poi
   */

  private PoI searchPoIByNode(Node node, String poi){
    for (PoI aux : this.PointsOfInterest){
      if (aux.name.equals(poi) && aux.location.equals(node)) return aux;
    }
    return null;
  }

  /**
   * make a visit
   * @param user user that visits
   * @param poIend poi to visit
   * @param timeStart time to start the trip
   * @param veiculo how he moves
   */


  public void makeAVisit(User user, PoI poIend, int timeStart, Veiculo veiculo){

    Iterable<DirectedEdge> directedEdges = this.cityMap.shortestPath(user.getCurrentLocation().id, poIend.location.id);

    ArrayList<Way> ways = new ArrayList<>();

    for(DirectedEdge edge : directedEdges){
      System.out.println(edge.from() + "->" + edge.to());
      ways.add(getWayBy2Nodes(searchNodebyId(intIDToLongID(edge.from())), searchNodebyId(intIDToLongID(edge.to()))));
    }
    System.out.println(poIend);
    user.visitAPoI(ways, timeStart, veiculo, poIend);
  }

  public Long intIDToLongID(Integer ID){
    for (Long i : this.getCityNodes().keys()){
      if (this.getCityNodes().get(i).id.equals(ID)) return i;
    }
    return 0L;
  }

}

