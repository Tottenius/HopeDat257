package model;

import model.FoodPackage.Foods;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class handles the data of a user
 */
public class UserData {

    public UserData(String user) {
        this.user = user;
    }

    // Username
    private final String user;

    // Logged in?
    private boolean loggedIn = false;

    public Map<String, List<Foods>> userData = new HashMap<>();


    public void addToUserData(String date, Foods food) {
        List<Foods> todayList;
        if (!userData.containsKey(date)) {
            todayList = new ArrayList<>();
        } else {
            todayList = userData.get(date);
        }
        todayList.add(food);
        userData.put(date , todayList);
    }

    public double getEmissions(String date) {
        // Get today's list of foods
        List<Foods> todayList = this.userData.get(date);
        double emissions = 0;
        // add all of the emissions of the day together
        for (Foods food: todayList) {
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