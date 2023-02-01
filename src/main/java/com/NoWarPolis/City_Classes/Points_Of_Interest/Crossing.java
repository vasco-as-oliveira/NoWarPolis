package com.NoWarPolis.City_Classes.Points_Of_Interest;

import com.NoWarPolis.City_Classes.Node;
import com.NoWarPolis.City_Classes.PoI;

public class Crossing  extends PoI {
    public Crossing(Node location, Integer poiId) {
        super(location, poiId);
        this.timespent=180;
        this.name="crossing";
    }

    @Override
    public String toString() {
        return "crossing" + "," + this.location.id + "," + this.location.idPos;
    }
}
