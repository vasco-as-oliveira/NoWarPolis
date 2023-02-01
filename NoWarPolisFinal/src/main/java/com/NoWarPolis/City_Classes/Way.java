package com.NoWarPolis.City_Classes;


public abstract class Way {

  private Integer id;
  private Long idPos;
  private Node from, to;
  private Double distance;
  private Double timeDuration;
  private Double averageVelocity;



  public Way(){}

  public Way(Integer id, Long idPos, Node from, Node to, Double distance) {
    this.averageVelocity=50.0;
    this.id = id;
    this.idPos = idPos;
    this.from = from;
    this.to = to;
    this.distance = distance;
  }

  /**
   * It calculates the time it takes to go in this way
   * @return the time it takes to go in this way
   */

  public Double time(){
    double velocityKMtoMS = this.averageVelocity * 1000;
    velocityKMtoMS /= 3600;
    return (this.getDistance()/velocityKMtoMS);
  }

  public Double getAverageVelocity() {
    return averageVelocity;
  }

  public void setAverageVelocity(Double averageVelocity) {
    this.averageVelocity = averageVelocity;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Node getFrom() {
    return from;
  }

  public void setFrom(Node from) {
    this.from = from;
  }

  public Node getTo() {
    return to;
  }

  public void setTo(Node to) {
    this.to = to;
  }

  public Long getIdPos() {
    return idPos;
  }

  public void setIdPos(Long idPos) {
    this.idPos = idPos;
  }

  public Double getDistance() {
    return distance;
  }

  public void setDistance(Double distance) {
    this.distance = distance;
  }

  public Double getTimeDuration() {
    return timeDuration;
  }

  public void setTimeDuration(Double timeDuration) {
    this.timeDuration = timeDuration;
  }

  /**
   * Takes from the file and parse the information to initialize the way, and because way is abstract, in each of his extends there's an override
   * @param s String to be parsed
   * @param i the index to be parsed
   * @return if its successfuly parsed
   */
  public boolean entered(String[] s, int i){
    return false;
  }


  @Override
  public String toString() {
    return "Way{" +
            "id=" + id +
            ", idPos=" + idPos +
            ", from=" + from +
            ", to=" + to +
            ", position=" + distance +
            '}';
  }
}