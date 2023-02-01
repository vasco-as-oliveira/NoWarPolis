package com.NoWarPolis.Util;
import com.NoWarPolis.City;

import java.io.Serializable;

public class Data implements Serializable {
    int day;
    int month;
    int year;

    public Data() {

    }

    public Data(int dia, int mes, int ano) {
        this.day=dia;
        this.month=mes;
        this.year=ano;
    }


    public String toString(){
        return String.valueOf(day) + '/' + String.valueOf(month) + '/' + String.valueOf(year);
    }
}
