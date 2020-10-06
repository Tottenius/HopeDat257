package model;

import model.FoodPackage.Foods;

//import java.text.DateFormat;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//import java.util.*;

public class UserData {

    public UserData(String user) {
        this.user = user;
    }

    // Username
    private String user;

    // Logged in?
    private boolean loggedIn = true;

    // Dateformat without time
    private DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    //Today's date
    private Date date;



    public Map<String, List<Foods>> userData = new HashMap<>();

    public void addToUserData(Date date, Foods food) {
        if (!userData.containsKey(date.toString())) {
            List<Foods> todaysList = new ArrayList<>();
            todaysList.add(food);
            userData.put(date.toString() , todaysList);
        } else {
            List<Foods> todaysList = userData.get(date.toString());
            todaysList.add(food);
            userData.put(date.toString(), todaysList);
        }
    }

    public double getEmissions(Date date) {

        for(String key : this.userData.keySet()){
            System.out.println("datum keys: "+ key);
        }

        List<Foods> todaysList = this.userData.get(date.toString());
        System.out.println("dagens datum: " + date);
        double emissions = 0;
        for (Foods food: todaysList) {
            emissions = emissions + food.getEmission();
        }
        return emissions;
    }

    public Map<String, List<Foods>> getUserData() {
        return this.userData;
    }


    public String getUser(){
        return this.user;
    }

    public boolean getLoggedIn(){
        return loggedIn;
    }
    public void setLoggedIn(boolean b){
        this.loggedIn = b;
    }



}
