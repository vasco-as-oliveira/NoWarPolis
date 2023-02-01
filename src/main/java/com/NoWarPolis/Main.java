package com.NoWarPolis;

import com.NoWarPolis.City_Classes.*;
import com.NoWarPolis.City_Classes.Points_Of_Interest.Abastecimento;
import com.NoWarPolis.City_Classes.Points_Of_Interest.Semaforo;
import com.NoWarPolis.City_Classes.Vehicles.Carro;
import edu.princeton.cs.algs4.DirectedEdge;

import java.util.ArrayList;


public class Main {

    public static void main(String[] args) throws Exception {
        //clientTest_R5_R6();
        //clientTest_R10();
        //clientTest_R11_and_R10(); //Usar para demonstrar o shortest path R11 a) com o R10 shortest path restricted
        //clientTest_R11a();
        //clientTest_R11b_c();
        //clientTest_R14();
        //clientTest_R15();
        //clientTest_R17();
        //clientTest_MakeAVisit();



    }


    private static void clientTest_R10() throws Exception {
        City NoWarPolis = new City(2);

        NoWarPolis.cityMap.addRestrictedNode(NoWarPolis.cityNodes.get(128564067L).id);

        for(Integer integer : NoWarPolis.cityMap.restrictedNodes){
            System.out.println(integer);
        }
    }

    private static void clientTest_R11_and_R10() throws Exception {
        City NoWarPolis = new City(2);

        NoWarPolis.cityMap.addRestrictedNode(NoWarPolis.cityNodes.get(128564072L).id);

        Iterable<DirectedEdge> directedEdges = NoWarPolis.cityMap.shortestPathRestricted(NoWarPolis.cityNodes.get(128564071L).id, NoWarPolis.cityNodes.get(128564067L).id);

        for (DirectedEdge edge : directedEdges) {
            System.out.println(edge.from() + "->" + edge.to());
        }


    }

    private static void clientTest_R11a() throws Exception {
        City NoWarPolis = new City(2);

        Iterable<DirectedEdge> directedEdges = NoWarPolis.cityMap.shortestPath(NoWarPolis.cityNodes.get(128560429L).id, NoWarPolis.cityNodes.get(8091600918L).id);

        for(DirectedEdge edge : directedEdges){
            System.out.println(edge.from() + "->" + edge.to());
            //ways.add(getWayBy2Nodes(searchNodebyId(intIDToLongID(edge.from())), searchNodebyId(intIDToLongID(edge.to()))));
        }
    }

    private static void clientTest_R11b_c() throws Exception {
        City NoWarPolis = new City(2);

        for(DirectedEdge edge : NoWarPolis.cityMap.subgraphWeighetTime_Highway.edges()){
            System.out.println(edge.from() + "->" + edge.to());
        }

        System.out.println(NoWarPolis.cityMap.isGrafoConexo(NoWarPolis.cityMap.subgraphWeighetDist_Highway, 0));
    }


    private static void clientTest_R14() throws Exception {

        City NoWarPolis = new City(2);
        DoubleWeightedGraph doubleWeightedGraph = new DoubleWeightedGraph();

        for(DirectedEdge directedEdge : doubleWeightedGraph.weightedTime.edges()){
            System.out.println(directedEdge.from() + "->" + directedEdge.to());
        }

        NoWarPolis.updateFile(1);

    }


    private static void clientTest_R15() throws Exception {
        City NoWarPolis = new City(2);

        System.out.println("a)" + NoWarPolis.getEtiquetas().get("abastecimento"));
        System.out.println("b)" + NoWarPolis.closestSem(NoWarPolis.cityNodes.get(128564067L)));

        NoWarPolis.updateFile(2);
    }


    private static void clientTest_R17(){
        City NoWarPolis = new City(2);
        ArrayList<Node[]> nodes = new ArrayList<>();
        Node[] twoNodes = new Node[] {NoWarPolis.cityNodes.get(128564067L), NoWarPolis.cityNodes.get(128564072L)};
        nodes.add(twoNodes);
        Node[] twoNodes1 = new Node[] {NoWarPolis.cityNodes.get(128560429L), NoWarPolis.cityNodes.get(8091600918L)};
        nodes.add(twoNodes1);


        NoWarPolis.cityMap.shortestPathBetweenLocationsTime(nodes, NoWarPolis.cityNodes.size());

        NoWarPolis.updateFile(2);
    }

    private static void clientTest_MakeAVisit() throws Exception {
        City NoWarPolis = new City(2);
        //System.out.println(((Semaforo)NoWarPolis.getPointsOfInterest().get(1)).carsStoppedBetweenTime(100, new Carro(0, NoWarPolis.users.get(31561252))));

        System.out.println(NoWarPolis.getPointsOfInterest().size());

        NoWarPolis.makeAVisit(NoWarPolis.users.get(31561252), NoWarPolis.PointsOfInterest.get(9), 450, NoWarPolis.users.get(31561252).getVeiculos().get(0));
        NoWarPolis.makeAVisit(NoWarPolis.users.get(43960319), NoWarPolis.PointsOfInterest.get(9), 1050, NoWarPolis.users.get(43960319).getVeiculos().get(0));
        NoWarPolis.makeAVisit(NoWarPolis.users.get(36693538), NoWarPolis.PointsOfInterest.get(9), 350, NoWarPolis.users.get(36693538).getVeiculos().get(0));
        NoWarPolis.makeAVisit(NoWarPolis.users.get(25547343), NoWarPolis.PointsOfInterest.get(9), 250, NoWarPolis.users.get(25547343).getVeiculos().get(0));
        NoWarPolis.makeAVisit(NoWarPolis.users.get(55367252), NoWarPolis.PointsOfInterest.get(0), 250, NoWarPolis.users.get(55367252).getVeiculos().get(0));
        NoWarPolis.makeAVisit(NoWarPolis.users.get(62390278), NoWarPolis.PointsOfInterest.get(1), 250, NoWarPolis.users.get(62390278).getVeiculos().get(0));
        NoWarPolis.makeAVisit(NoWarPolis.users.get(80183537), NoWarPolis.PointsOfInterest.get(2), 150, NoWarPolis.users.get(80183537).getVeiculos().get(0));
        NoWarPolis.makeAVisit(NoWarPolis.users.get(80426025), NoWarPolis.PointsOfInterest.get(3), 250, NoWarPolis.users.get(80426025).getVeiculos().get(0));
        NoWarPolis.makeAVisit(NoWarPolis.users.get(88508474), NoWarPolis.PointsOfInterest.get(4), 350, NoWarPolis.users.get(88508474).getVeiculos().get(0));
        NoWarPolis.makeAVisit(NoWarPolis.users.get(56526374), NoWarPolis.PointsOfInterest.get(5), 450, NoWarPolis.users.get(56526374).getVeiculos().get(0));


        for(PoI poI : NoWarPolis.getPointsOfInterest()){
            if(poI instanceof Semaforo){
                for (Integer keys : ((Semaforo)poI).carsStoppedAtTime.keys()){
                    for(Veiculo veiculo : ((Semaforo)poI).carsStoppedAtTime.get(keys))
                        System.out.println("At the time:"+ keys*100 + "->" +veiculo.getOwner().getUsername());
                }
            }
        }

        for(Visit visit : NoWarPolis.getUsers().get(80426025).getVisits().values()){
            System.out.println(visit.getDistance());
        }

        NoWarPolis.updateFile(2);
    }

    private static void clientTest_CreatePois(){
        City NoWarPolis = new City(2);
        int index=2;

        PoI toAdd = new Abastecimento(NoWarPolis.getListOfWays(index).get(NoWarPolis.getListOfWays(index).size()-1).getTo(), NoWarPolis.getPointsOfInterest().size());
        try {
            NoWarPolis.addEtiqueta_Node(toAdd);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(NoWarPolis.getPointsOfInterest());


        NoWarPolis.updateFile(1);
    }

    private static void clientTest_remove(){
        City NoWarPolis = new City(2);
        System.out.println("All Users:");
        System.out.println(NoWarPolis.getUsers().values());
        NoWarPolis.removeUser(80183537);
        System.out.println("After removing");
        System.out.println(NoWarPolis.getUsers().values());

        System.out.println("All Points of Interest");
        System.out.println(NoWarPolis.getEtiquetas());
        System.out.println(NoWarPolis.getPointsOfInterest());
        for(Long key : NoWarPolis.getCityNodes().keys())
            System.out.println(NoWarPolis.getCityNodes().get(key));

        NoWarPolis.removePointOfInterest(NoWarPolis.getPointsOfInterest().get(0));

        System.out.println("All removing a Point of Interest");
        System.out.println(NoWarPolis.getEtiquetas());
        System.out.println(NoWarPolis.getPointsOfInterest());
        for(Long key : NoWarPolis.getCityNodes().keys())
            System.out.println(NoWarPolis.getCityNodes().get(key));


        NoWarPolis.updateFile(1);
    }

    public static void clientTest_R5_R6(){
        City NoWarPolis = new City(2);
        /*System.out.println(NoWarPolis.usersThatVisitedPoIBetweenTimes(NoWarPolis.getPointsOfInterest().get(1), 0, 200));
        System.out.println(NoWarPolis.PoIsNOTvisitedBetweenTimes(9, 10));
        System.out.println(NoWarPolis.top5MostVisitedPoisBetweenTimes(0, 400));
        System.out.println(NoWarPolis.TopFiveUsers_VisitedBiggerNumberOfPoIsBetweenTimes(200, 300));*/

        NoWarPolis.now(500);



        //NoWarPolis.updateFile(1);
    }



}

