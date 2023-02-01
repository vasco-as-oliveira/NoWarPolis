package com.NoWarPolis.City_Classes;


import java.io.Serializable;

public abstract class Veiculo implements Serializable {

  /**
   * The id of the vehicle
   * the owner of the vehicle
   * the type of vehicle
   * the maximum velocity this vehicle has
   */

  private Integer id;
  private User owner;
  public String type;
  public double maximumVelocity;


  public Veiculo(Integer id, User owner) {
    this.id = id;
    this.owner = owner;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public User getOwner() {
    return owner;
  }

  public void setOwner(User person) {
    this.owner = person;
  }

  @Override
  public String toString() {
    return ""+id;

  }
}