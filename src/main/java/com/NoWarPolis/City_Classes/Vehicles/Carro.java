package com.NoWarPolis.City_Classes.Vehicles;

import com.NoWarPolis.City_Classes.User;
import com.NoWarPolis.City_Classes.Veiculo;

public class Carro extends Veiculo {

    public Carro(int id, User owner) {
        super(id, owner);
        type = "car";
        maximumVelocity = 50.0 * 13.8889;
    }

    @Override
    public String toString() {
        return  this.type + "," + getId() + "," + getOwner().getId();
    }
}