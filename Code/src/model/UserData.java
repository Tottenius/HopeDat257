package model;

import model.FoodPackage.Foods;
import java.sql.Date;
import java.util.*;

public class UserData {

    public UserData(String user) {
        this.user = user;
    }

    // Username
    private String user;

    // Logged in?
    private boolean loggedIn = false;

    public Map<String, List<Foods>> userData = new HashMap<>();


    public void addToUserData(String date, Foods food) {
        if (!userData.containsKey(date)) {
            List<Foods> todaysList = new ArrayList<>();
            todaysList.add(food);
            userData.put(date , todaysList);
        } else {
            List<Foods> todaysList = userData.get(date);
            todaysList.add(food);
            userData.put(date, todaysList);
        }
    }

    public double getEmissions(String date) {
        // Get todays list of foods

        List<Foods> todaysList = this.userData.get(date);

        double emissions = 0;
        // add all of the emissions of the day together
        for (Foods food: todaysList) {
            emissions = food.getEmission();
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