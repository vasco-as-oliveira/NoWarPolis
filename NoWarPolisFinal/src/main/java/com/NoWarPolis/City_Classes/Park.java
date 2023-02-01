package com.NoWarPolis.City_Classes;

import java.util.Arrays;

public class Park extends Way {
    private String city;
    private String postcode;
    private String street;
    private String[] alt_name;
    private String website;
    private boolean dog;
    private String leisure;
    private String name;
    private String opening_hours;


    /**
     *
     * @param id id of the way
     * @param idPos longer id of the way
     * @param from the position it started
     * @param to the position it ended
     * @param distance the way distance
     */

    public Park(Integer id, Long idPos, Node from, Node to, Double distance) {
        super(id, idPos, from, to, distance);
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public boolean isDog() {
        return dog;
    }

    public void setDog(boolean dog) {
        this.dog = dog;
    }

    public String getLeisure() {
        return leisure;
    }

    public void setLeisure(String leisure) {
        this.leisure = leisure;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOpening_hours() {
        return opening_hours;
    }

    public void setOpening_hours(String opening_hours) {
        this.opening_hours = opening_hours;
    }

    @Override
    public boolean entered(String[] s, int i){
        switch (s[i]) {
            case "addr:city" -> this.city = s[i + 1];
            case "addr:postcode" -> this.postcode = s[i + 1];
            case "addr:street" -> this.street = s[i + 1];
            case "alt_name" -> this.alt_name = s[i + 1].split(";");
            case "contact:website" -> this.website = s[i + 1];
            case "dog" -> this.dog = s[i + 1].equals("yes");
            case "leisure" -> this.leisure = s[i + 1];
            case "name" -> this.name = s[i + 1];
            case "opening_hours" -> this.opening_hours = s[i + 1];
        }
        return false;
    }

    @Override
    public String toString() {
        return "Park{" +
                "city='" + city + '\'' +
                ", postcode='" + postcode + '\'' +
                ", street='" + street + '\'' +
                ", alt_name=" + Arrays.toString(alt_name) +
                ", website='" + website + '\'' +
                ", dog=" + dog +
                ", leisure='" + leisure + '\'' +
                ", name='" + name + '\'' +
                ", opening_hours='" + opening_hours + '\'' +
                "} " + super.toString();
    }
}
