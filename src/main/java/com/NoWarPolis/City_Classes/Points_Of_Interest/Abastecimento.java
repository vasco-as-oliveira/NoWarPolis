package com.NoWarPolis.City_Classes.Points_Of_Interest;

import com.NoWarPolis.City_Classes.Node;
import com.NoWarPolis.City_Classes.PoI;

public class Abastecimento extends PoI {

    /**
     * @param location the location where it is
     * @param id the id of the Poi
     */

    public Abastecimento(Node location, Integer id) {
        super(location, id);
        /**
         * The time spent in this poi and the name of it
         */
        this.timespent=180;
        this.name="abastecimento";
    }


    @Override
    public String toString() {
        return "abastecimento" + "," + this.location.id + "," + this.location.idPos;
    }
}