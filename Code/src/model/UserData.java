package model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class UserData {

    // Username
    private String user;

    // Dateformat without time
    private DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    // Data for each day stored in a hashmap where the key is the day it was put in without the time
    private Map<Date,Integer> emissions = new HashMap<>();

    //Today's date
    private Date date;

    public Map<Date, Integer> getEmissionsMap(){
        return this.emissions;
    }

    // add emissions to the current date
    public void addToEmissions( int newEmissions) throws ParseException {
        // Get today's date without time
        this.date = formatter.parse(formatter.format(new Date()));
        // Get old emissions from today
        int currentEmission = 0;
        // if there is something on the day add it
        if (this.emissions.get(this.date) != null) {
            currentEmission =+ this.emissions.get(this.date);
        }
        // Add new emissions
        currentEmission = currentEmission + newEmissions;
        // Update hashmap with new values
        this.emissions.put(date, currentEmission);
    }

        // same but for a custom date
    public void addToEmissions( int newEmissions, Date chosenDate) throws ParseException {
        // format it for safety
        chosenDate = formatter.parse(formatter.format(chosenDate));
        // Get old emissions from today
        int currentEmission = 0;
        // if there is something on the day add it
        if (this.emissions.get(chosenDate) != null) {
            currentEmission =+ this.emissions.get(chosenDate);
        }
        // Add new emissions
        currentEmission = currentEmission + newEmissions;
        // Update hashmap with new values
        this.emissions.put(chosenDate, currentEmission);

    }

    public int getEmissions(Date date){
        System.out.println(this.user +"'s emissions for the date "+date+ " is: " + this.emissions.get(date));
        return this.emissions.get(date);
    }

    public UserData(String user) {
        this.user = user;
    }
}
