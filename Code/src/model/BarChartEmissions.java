package model;

import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import model.FoodPackage.Foods;

import java.sql.Date;

import java.util.*;

public class BarChartEmissions {
    // todays time
    private final long today = System.currentTimeMillis();
    // The chosen date
    private Date chosenDate;
    // The series
    private XYChart.Series emissionSeries = new XYChart.Series<>();
    // The barChart
    private BarChart barChart;
    // The collection
    private ArrayList<XYChart.Data<String,Double>> samples = new ArrayList<>();

    // The comparator to sort the collection of dates
    private static Comparator<XYChart.Data<String,Double>> inDateOrder(){
        //Sort on the value of the date
        return Comparator.comparing(a -> Date.valueOf(a.getXValue()));

    }

    // Set the date
    public void setDate (Date date){
        this.chosenDate = date;
        this.setUpCollectionForDate();
    }

    // Constructor
    public BarChartEmissions(BarChart barChartOne, Date date ){
        this.chosenDate = date;
        this.barChart = barChartOne;
        barChart.getData().add(this.emissionSeries);
        // There seams to be an error if you animate the chart.....
        barChart.setAnimated(false);
    }

    public void clearEmissionCollection(){
        this.samples.clear();
    }

    private void setUpCollectionForDate(){
        Date earliestDate = Date.valueOf(samples.get(0).getXValue());
        Date latestDate = Date.valueOf(samples.get(samples.size()-1).getXValue());
        if(samples.size() > 7){
            if ( chosenDate.compareTo(latestDate) == -1){
                this.samples.remove(samples.size()-1);
            }
            else{
                this.samples.remove(0);
            }
        }
    }

    public void addToChart(String date, double emission){

        XYChart.Data newData = new XYChart.Data(date.toString(), emission);
        //Loop through the existing data
        for(XYChart.Data<String,Double> data : samples){
            // If there is a value on the day update it
            if(data.getXValue().equals(date.toString())){
                // add the new emission to the old one
                double newY = data.getYValue() + emission;
                data.setYValue(newY);
                // exist method
                return;
            }
        }
        //Otherwise add it
        this.samples.add(newData);
        // Sort the data
        Collections.sort(samples, inDateOrder());
        //Make the list show the seven days
        this.setUpCollectionForDate();
        // Remove the old series
        this.emissionSeries.getData().clear();
        // add the updated one
        this.emissionSeries.getData().addAll(samples);
        //Set colour
        for(Node n:barChart.lookupAll(".default-color0.chart-bar")) {
            n.setStyle("-fx-bar-fill: #3A506B;");
        }
    }

    public void updateBarChart(UserData user, Date date) {
        //Reset the barchart values
        this.clearEmissionCollection();
        // get the map with values
        Map<String, List<Foods>> userDataMap = user.getUserData();
        // Get tye date
        Date loopDate = date;
        // Get the date in milisec for loop
        long todayMilisec = date.getTime();
        for (int i = 0; i < 7; i++) {
            // Update to the right day
            loopDate.setTime(todayMilisec + i * (1000 * 60 * 60 * 24));
            // Get the String of the Date
            String stringDate = loopDate.toString();
            //See if there is a value there
            if (userDataMap.containsKey(stringDate)) {
                // if it is add it
                this.addToChart(stringDate, user.getEmissions(stringDate));
            } else {
                // if not add a zero to the graph on that date
                this.addToChart(stringDate, 0);
            }
        }
        // I change the date back to today, it seams to be static and change the date everywhere
        loopDate.setTime(todayMilisec);
    }
}
