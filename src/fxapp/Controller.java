package fxapp;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.NumberStringConverter;

public class Controller {
    ObservableList datalist;
    @FXML
    private TableView<XYChart.Data<Number, Number>> dataTableView;
    @FXML
    private TableColumn<XYChart.Data<Number, Number>, Number> xColumn;
    @FXML
    private TableColumn<XYChart.Data<Number, Number>, Number> yColumn;
    @FXML
    private LineChart<Number, Number> lineChart;
    @FXML
    private NumberAxis xAxis;
    @FXML
    private NumberAxis yAxis;
    @FXML
    private TextField xAddField;
    @FXML
    private TextField yAddField;

    @FXML
    private void deleteRow() {
        if (dataTableView.getFocusModel().getFocusedCell().getRow() >= 0) {
            datalist.remove(dataTableView.getFocusModel().getFocusedIndex());
        }
    }

    @FXML
    private void addRow() {

        if(!xAddField.getText().equals("")||!yAddField.getText().equals(""))
        try {
            NumberStringConverter converter = new NumberStringConverter();
            datalist.add(new XYChart.Data<Number, Number>(converter.fromString(xAddField.getText()),
                    converter.fromString(yAddField.getText())));
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    public Controller() {
    }


    @FXML
    private void initialize() {

        datalist = Model.getInstance().getChartData();
        dataTableView.setEditable(true);

        xColumn.setCellValueFactory(cellData -> cellData.getValue().XValueProperty());
        yColumn.setCellValueFactory(cellData -> cellData.getValue().YValueProperty());

        xColumn.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));
        yColumn.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));

        dataTableView.setItems(datalist);
        XYChart.Series<Number, Number> series = new XYChart.Series<>(datalist);
        series.setData(datalist);
        series.setName("y=F(x)");
        lineChart.getData().add(series);
        // dummy
        datalist.add(new XYChart.Data<>(10.1, 10.2));
        datalist.add(new XYChart.Data<>(5.1, 5.2));
        datalist.add(new XYChart.Data<>(1.1, 1.2));
    }
}

