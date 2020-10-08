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

    public Map<String, List<Foods>> userData = new HashMap<>();

    public synchronized void addToUserData(Date date, Foods food) {
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
        // Get todays list of foods
        List<Foods> todaysList = this.userData.get(date.toString());

        double emissions = 0;
        // add all of the emissions of the day together
        for (Foods food: todaysList) {
            emissions = emissions + food.getEmission();
        }
        // Return the sum of all emissions for the day
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
