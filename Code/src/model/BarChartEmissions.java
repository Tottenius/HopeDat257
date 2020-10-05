package model;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

import java.util.Date;

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
        emissionSeries.getData().add(new XYChart.Data("" +date, emission));

    }
}
