package model;

import model.FoodPackage.Foods;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class UserData {

    public UserData(String user) {
        this.user = user;
    }

    // Username
    private String user;

    // Logged in?
    private boolean loggedIn = false;

    // Dateformat without time
    private DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    //Today's date
    private Date date;



    public Map<Date, List<Foods>> userData = new HashMap<>();

    public void addToUserData(Date date, Foods food) {
        if (userData.get(date) == null) {
            List<Foods> todaysList = new ArrayList<>();
            todaysList.add(food);
            userData.put(date , todaysList);
        } else {
            List<Foods> todaysList = userData.get(date);
            todaysList.add(food);
            userData.put(date, todaysList);
        }
    }

    public double getEmissions(Date date) {
        List<Foods> todaysList = this.userData.get(date);
        double emissions = 0;
        for (Foods food: todaysList) {
            emissions = emissions + food.getEmission();
        }
        return emissions;
    }

    public Map<Date, List<Foods>> getUserData() {
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
