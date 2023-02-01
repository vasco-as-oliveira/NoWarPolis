package com.NoWarPolis.City_Classes.Vehicles;

import com.NoWarPolis.City_Classes.User;
import com.NoWarPolis.City_Classes.Veiculo;

public class Pedestre extends Veiculo {

    public Pedestre(int id, User owner) {
        super(id, owner);
        type = "pedestre";
        maximumVelocity = 10.0 * 13.8889;
    }

    @Override
    public String toString() {
        return  this.type + "," + getId() + "," + getOwner().getId();
    }
}
