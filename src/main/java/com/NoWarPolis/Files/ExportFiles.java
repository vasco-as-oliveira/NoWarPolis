package com.NoWarPolis.Files;

import com.NoWarPolis.City_Classes.*;
import com.NoWarPolis.Util.Data;

import java.io.FileWriter;
import java.util.ArrayList;

public abstract class ExportFiles {

    public static void addUsers(String username, String password, Data birth, int cc, ArrayList<Veiculo> Veiculos, Node currentLocation){
        try{
            FileWriter f1 = new FileWriter("user.txt");
            f1.write("id:" + cc + ";Username:" + username + ";Password:" + password + ";Birthdate:" + birth + ";Vehicles:" + Veiculos + ";" + currentLocation );
            f1.close();
        } catch (Exception f){
            System.out.println(f);
        }
        System.out.println("Users added with success");
    }

}
