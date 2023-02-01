package com.NoWarPolis.City_Classes;

public class Carro extends Veiculo {

    public Carro(int id, User owner) {
        super(id, owner);
        type = "car";
        maximumVelocity = 50.0 * 13.8889;
    }

    @Override
    public String toString() {
        return "Carro{" + getId() + ", "+ getOwner().getUsername() + "}";
    }
}