import Util.Data;
import edu.princeton.cs.algs4.BST;
import edu.princeton.cs.algs4.Graph;
import City.Node;
import java.util.ArrayList;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        BST<Integer, Integer> times = new BST<>();
        times.put(6,6);
        times.put(1,1);
        times.put(8,8);

        ArrayList<Node> points = new ArrayList<>();
        for(int i=0; i<10; i++) {
            ArrayList<Integer> coord1 = new ArrayList<>();
            coord1.add((int) (Math.random() * 10));
            coord1.add((int) (Math.random() * 10));
            Node n1 = new Node(i, coord1, null);
            points.add(n1);
        }

        City noWarPolis = new City(10, times, points);

        noWarPolis.defineEdges(2, 8);
        noWarPolis.defineEdges(9, 7);
        System.out.println("HELLOOOOOOOOOOOOOOOO:");
        for(int i : noWarPolis.nodesCordinates.keys()){

            //for(int a : noWarPolis.cityMap.adj(i)){
                System.out.println(noWarPolis.nodesCordinates.get(i));
                //System.out.println("Vertice: "+i+"\tEdge: "+a);
            //}
        }
        System.out.println("Vertice: " + noWarPolis.nodesCordinates.get(5).get(0));


        System.out.println(noWarPolis.searchNodebyCoordinates(noWarPolis.nodesCordinates.get(3).get(0), noWarPolis.nodesCordinates.get(3).get(1)).toString());


        System.out.println(noWarPolis.timestamps.max());
    }
}
