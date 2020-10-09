package model;

import javafx.application.Platform;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

import java.sql.Date;
import java.util.concurrent.TimeUnit;

public class BarChartEmissions {
    // The series
   private final XYChart.Series emissionSeries = new XYChart.Series();
    // The barChart
    private BarChart barChart;

    public BarChartEmissions(BarChart barChartOne) {
        this.barChart = barChartOne;
        barChart.getData().add(this.emissionSeries);

    }

    public void addToChart(Date date, double emission){

        Platform.runLater(()->{
            try {
                XYChart.Data newData = new XYChart.Data("" + date, emission);
                emissionSeries.getData().add(newData);
            }
            catch(IndexOutOfBoundsException e){
                // Try again. Verkar inte fånga excetionet men får skräpa här ett tag till ändå
                System.out.println("Tries to add to chart again");
                addToChart(date, emission);
            }
        });


    }
}
