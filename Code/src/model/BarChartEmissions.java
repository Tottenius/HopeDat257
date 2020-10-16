package model;

import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

import java.awt.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

public class BarChartEmissions {
    // The series
    private XYChart.Series emissionSeries = new XYChart.Series<>();
    // The barChart
    private BarChart barChart;
    // The collection
    private Collection<XYChart.Data<String,Double>> samples = new ArrayList<>();

    public BarChartEmissions(BarChart barChartOne) {
        this.barChart = barChartOne;
        barChart.getData().add(this.emissionSeries);
        // There seams to be an error if you animate the chart.....
        barChart.setAnimated(false);
    }

    public void clearEmissionCollection(){
        this.samples.clear();
    }

    public void addToChart(String date, double emission){

        XYChart.Data newData = new XYChart.Data(date, emission);
        //Loop through the existing data
        for(XYChart.Data<String,Double> data : samples){
            // If there is a value on the day update it
            if(data.getXValue().equals(date)){
                // add the new emission to the old one
                double newY = data.getYValue() + emission;
                data.setYValue(newY);
                // exist method
                return;
            }
        }
       // System.out.println("Kommer vi hit?");
        //Otherwise add it
        this.samples.add(newData);
        // Remove the old series
        this.emissionSeries.getData().clear();
        // add the updated one
        this.emissionSeries.getData().addAll(samples);
        //Set colour
        for(Node n:barChart.lookupAll(".default-color0.chart-bar")) {
            n.setStyle("-fx-bar-fill: #3A506B;");
        }



    }
}
