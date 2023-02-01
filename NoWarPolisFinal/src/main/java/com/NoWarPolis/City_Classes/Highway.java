package com.NoWarPolis.City_Classes;

import java.util.Arrays;

public class Highway extends Way{
    private String[] alt_name;
    private String type;
    private Integer lanes;
    private String loc_name;
    private String name;
    private boolean oneWay;
    private String surface;
    private boolean lit;
    private boolean sidewalk;

    public Highway(Integer id, Long idPos, Node from, Node to, Double position) {
        super(id, idPos, from, to, position);
    }

    public String[] getAlt_name() {
        return alt_name;
    }

    public void setAlt_name(String[] alt_name) {
        this.alt_name = alt_name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getLanes() {
        return lanes;
    }

    public void setLanes(Integer lanes) {
        this.lanes = lanes;
    }

    public String getLoc_name() {
        return loc_name;
    }

    public void setLoc_name(String loc_name) {
        this.loc_name = loc_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOneWay() {
        return oneWay;
    }

    public void setOneWay(boolean oneWay) {
        this.oneWay = oneWay;
    }

    public String getSurface() {
        return surface;
    }

    public void setSurface(String surface) {
        this.surface = surface;
    }

    public boolean isLit() {
        return lit;
    }

    public void setLit(boolean lit) {
        this.lit = lit;
    }

    public boolean isSidewalk() {
        return sidewalk;
    }

    public void setSidewalk(boolean sidewalk) {
        this.sidewalk = sidewalk;
    }

    @Override
    public boolean entered(String[] s, int i){
        switch (s[i]) {
            case "alt_name" -> this.alt_name = s[i + 1].split(";");
            case "highway" -> this.type = s[i + 1];
            case "lanes" -> this.lanes = Integer.parseInt(s[i + 1]);
            case "loc_name" -> this.loc_name = s[i + 1];
            case "name" -> this.name = s[i + 1];
            case "oneway" -> this.oneWay = s[i + 1].equals("yes");
            case "surface" -> this.surface = s[i + 1];
            case "lit" -> this.lit = s[i + 1].equals("yes");
            case "sidewalk" -> this.sidewalk = s[i + 1].equals("yes");
        }
        return false;
    }

    @Override
    public String toString() {
        return "Highway{" +
                "alt_name=" + Arrays.toString(alt_name) +
                ", type='" + type + '\'' +
                ", lanes=" + lanes +
                ", loc_name='" + loc_name + '\'' +
                ", name='" + name + '\'' +
                ", oneWay=" + oneWay +
                ", surface='" + surface + '\'' +
                ", lit=" + lit +
                ", sidewalk=" + sidewalk +
                "} " + super.toString();
    }
}
