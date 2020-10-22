package model;

import model.FoodPackage.Foods;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class handles the data of a user
 *
 */
public class UserData {

    public UserData(String user) {
        this.user = user;
    }

    // Username
    private final String user;

    // Logged in?
    private boolean loggedIn = false;
    // Map with the date as a key and a list with foods for that date.
    private Map<String, List<Foods>> userData = new HashMap<>();

    /**
     * This method adds a food to a certain date
     * @param date The date that food is being added to
     * @param food The food that is added
     */
    public void addToUserData(String date, Foods food) {
        // Create a temp list for the day
        List<Foods> todayList;
        if (!userData.containsKey(date)) {
            // If there is nothing on todays date, make a new list for the date
            todayList = new ArrayList<>();
        } else {
            // else get the list that exists on the date
            todayList = userData.get(date);
        }
        // Add the food to the list
        todayList.add(food);
        // Put the list back in the map on the right date
        userData.put(date , todayList);
    }

    /**
     *
     * @param date The date
     * @return Returns the emissions of the day as a double
     */
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

    /**
     * Returns the map with emissions for the user
     * @return Returns the map with emissions for the user
     */
    public Map<String, List<Foods>> getUserData() {
        return this.userData;
    }

    /**
     * Returns the users name
     * @return Returns the users name
     */
    public String getUser(){
        return this.user;
    }

    /**
     * Returns if the user is logged in or not
     * @return  Returns if the user is logged in or not
     */
    public boolean getLoggedIn(){
        return loggedIn;
    }

    /**
     * Sets the users logged in status
     * @param b If true the user will be set to logged in
     */
    public void setLoggedIn(boolean b){
        this.loggedIn = b;
    }
}