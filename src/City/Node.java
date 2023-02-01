package City;

import java.util.ArrayList;

public class Node {

    public Node(Integer id, ArrayList<Integer> coordinates, ArrayList<PoI> pointsOfInteress) {
        this.id = id;
        this.coordinates = coordinates;
        this.pointsOfInteress = pointsOfInteress;
    }
    public Integer id;
    public ArrayList<Integer> coordinates; // 0 - latitude 1 - longitude
    public ArrayList<PoI> pointsOfInteress;

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                ", coordinates=" + coordinates +
                ", pointsOfInteress=" + pointsOfInteress +
                '}';
    }
}