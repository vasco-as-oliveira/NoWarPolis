package com.NoWarPolis.City_Classes.Vehicles;

import com.NoWarPolis.City_Classes.User;
import com.NoWarPolis.City_Classes.Veiculo;

public class Trotinete extends Veiculo {
    public Trotinete(Integer id, User owner) {
        super(id, owner);
        type = "trotinete";
        maximumVelocity = 10 * 13.8889;
    }

    @Override
    public String toString() {
        return  this.type + "," + getId() + "," + getOwner().getId();
    }
}