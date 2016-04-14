package fxapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

/**
 * Created by Никита on 13.04.2016.
 */
public class Model {
    private static volatile Model instance;

    private ObservableList<XYChart.Data<Number,Number>> chartData = FXCollections.observableArrayList();

    public  ObservableList<XYChart.Data<Number,Number>> getChartData(){
        return chartData;
    }

    public static Model getInstance() {
        Model localInstance = instance;
        if (localInstance == null) {
            synchronized (Model.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new Model();
                }
            }
        }
        return localInstance;
    }
}
