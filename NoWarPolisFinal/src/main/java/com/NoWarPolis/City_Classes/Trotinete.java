package com.NoWarPolis.City_Classes;

public class Trotinete extends Veiculo {
    public Trotinete(Integer id, User owner) {
        super(id, owner);
        type = "trotinete";
        maximumVelocity = 10 * 13.8889;
    }
}