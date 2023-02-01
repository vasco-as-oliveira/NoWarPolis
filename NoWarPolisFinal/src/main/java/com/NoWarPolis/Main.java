package com.NoWarPolis;

import com.NoWarPolis.City_Classes.*;
import com.NoWarPolis.Util.Data;

import edu.princeton.cs.algs4.DirectedEdge;


public class Main {

    public static void main(String[] args) throws Exception {
        City NoWarPolis = new City(2);

        //System.out.println(NoWarPolis.getUsers().keys());


        //clientTest_remove();
       // NoWarPolis.updateFile();
        //User aux = new User("nuno", "nuno", new Data(1,2,3), 0, null, null);
       // NoWarPolis.cityNodes.get(128564067L).AddInterruption(8, 88);
        /*aux.poisNOTVisitedBetweenTimes(0, 1);
        NoWarPolis.addEtiqueta_Node(new Semaforo(1,NoWarPolis.cityNodes.get(128564067L), 91 ));
        NoWarPolis.addEtiqueta_Node(new Semaforo(1,NoWarPolis.cityNodes.get(128560439L), 92 ));
        NoWarPolis.addEtiqueta_Node(new Semaforo(1,NoWarPolis.cityNodes.get(128564068L), 93 ));
        NoWarPolis.addEtiqueta_Node(new Semaforo(1,NoWarPolis.cityNodes.get(411834913L), 99 ));
        NoWarPolis.addEtiqueta_Node(new Semaforo(1,NoWarPolis.cityNodes.get(128564072L), 69 ));

        System.out.println(NoWarPolis.closestSem(NoWarPolis.cityNodes.get(128564067L)).get(0).PoiId);
        System.out.println(NoWarPolis.closestSem(NoWarPolis.cityNodes.get(128564067L)).get(1).PoiId);
        System.out.println(NoWarPolis.closestSem(NoWarPolis.cityNodes.get(128564067L)).get(2).PoiId);*/

        NoWarPolis.updateFile(1);
    }

    private static void clientTest_MakeAVisit(){
        City NoWarPolis = new City(2);
        Veiculo veiculo = new Carro(0, NoWarPolis.searchUserById(31561252));

        NoWarPolis.getUsers().get(31561252).visitAPoI(NoWarPolis.getListOfWays(2), 0, veiculo, NoWarPolis.getPointsOfInterest().get(1));
        NoWarPolis.updateFile(1);
    }

    private static void clientTest_CreatePois(){
        City NoWarPolis = new City(2);
        int index=2;

        PoI toAdd = new Abastecimento(NoWarPolis.getListOfWays(index).get(NoWarPolis.getListOfWays(index).size()-1).getTo(), NoWarPolis.getPointsOfInterest().size());
        NoWarPolis.addEtiqueta_Node(toAdd);

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

    public static void clientTest_tops(){
        City NoWarPolis = new City(2);
        System.out.println(NoWarPolis.usersThatVisitedPoIBetweenTimes(NoWarPolis.getPointsOfInterest().get(1), 140, 195));
        System.out.println(NoWarPolis.PoIsNOTvisitedBetweenTimes(9, 10));
        System.out.println(NoWarPolis.top5MostVisitedPoisBetweenTimes(100, 200));
        System.out.println(NoWarPolis.TopFiveUsers_VisitedBiggerNumberOfPoIsBetweenTimes(100, 200));





        NoWarPolis.updateFile(1);
    }



}

