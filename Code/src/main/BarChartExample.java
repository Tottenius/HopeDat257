package main;

import javafx.scene.*;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

public class BarChartExample {

    XYChart.Series series1 = new XYChart.Series();
    XYChart.Series series2 = new XYChart.Series();

    public BarChartExample(BarChart barChartOne) {
        series1.getData().add(new XYChart.Data("Dag 1", 4));
        series2.getData().add(new XYChart.Data("Dag 2", 8));
        barChartOne.getData().add(series1);
        barChartOne.getData().add(series2);

        System.out.println("hej");

    }
}
