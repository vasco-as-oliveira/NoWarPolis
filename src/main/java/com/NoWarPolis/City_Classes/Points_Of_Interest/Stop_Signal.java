package com.NoWarPolis.City_Classes.Points_Of_Interest;

import com.NoWarPolis.City_Classes.Node;
import com.NoWarPolis.City_Classes.PoI;

public class Stop_Signal  extends PoI {
    public Stop_Signal(Node location, Integer poiId) {
        super(location, poiId);
        this.timespent=180;
        this.name="stop_signal";
    }

    @Override
    public String toString() {
        return "stop_signal" + "," + this.location.id + "," + this.location.idPos;
    }
}
