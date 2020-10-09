package model;

import javafx.application.Platform;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

    public void addToChart(Date date, double emission){

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
        System.out.println("Kommer vi hit?");
        //Otherwise add it
        this.samples.add(newData);
        // Remove the old series
        this.emissionSeries.getData().clear();
        // add the updated one
        this.emissionSeries.getData().addAll(samples);




    }
}
