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
        //emissionSeries.getData().addAll(new XYChart.Data("" +date, emission));
        int index = this.emissionSeries.getData().size();
        XYChart.Data newData = new XYChart.Data("" +date, emission);
        emissionSeries.getData().add(index ,newData);

    }
}
